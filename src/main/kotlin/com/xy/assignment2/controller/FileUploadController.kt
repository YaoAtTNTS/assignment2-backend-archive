package com.xy.assignment2.controller

import com.xy.assignment2.entity.InvoiceEntity
import com.xy.assignment2.exception.SQLOperationException
import com.xy.assignment2.service.impl.InvoiceServiceImpl
import com.xy.assignment2.utils.JsonUtils
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import javax.annotation.Resource

/**
 * @ Author: Xiong Yao
 * @ Date: Created at 2:35 PM 11/6/2022
 * @ Description:
 * @ Version: 1.0
 * @ Email: gongchen711@gmail.com
 *
 */

@RestController
@RequestMapping("/invoice")
@CrossOrigin(originPatterns = ["*"], allowCredentials = "true")
class FileUploadController {

    @Resource
    private lateinit var invoiceService: InvoiceServiceImpl

    @Volatile
    private var uploadedRecordsAmount = 0

    @Volatile
    private var uploadStopped = false

    private val lock: Lock = ReentrantLock()

    @GetMapping("/upload/progress")
    fun uploadProgress(): ResponseEntity<String> {
        return if (uploadStopped) {
            ResponseEntity.ok().body(JsonUtils.toJsonString("result", -1))
        } else ResponseEntity.ok().body(JsonUtils.toJsonString("result", uploadedRecordsAmount))
    }

    @RequestMapping(value = ["/upload"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun upload(file: MultipartFile): ResponseEntity<String> {
        return if (lock.tryLock()) {
            val tempDir: String
            tempDir = if (System.getProperty("os.name").lowercase(Locale.getDefault()).startsWith("windows")) {
                "C:\\temp\\xiong\\"
            } else {
                "/tmp/xiong/"
            }
            val filename = file.originalFilename
            val tempPath = tempDir + filename + UUID.randomUUID()
            val dir = File(tempPath)
            var result: String? = null
            if (!dir.exists()) {
                dir.mkdirs()
            }
            try {
                file.transferTo(dir)
                result = readCSVData(dir)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                lock.unlock()
                dir.delete()
            }
            if (result == null) {
                return ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", "Unknown error."))
            }
            if (result.matches(Regex("^[0-9]+.*"))) {
                ResponseEntity.ok().body(JsonUtils.toJsonString("result", result))
            } else ResponseEntity.badRequest().body(JsonUtils.toJsonString("result", result))
        } else {
            ResponseEntity.status(503).body(JsonUtils.toJsonString("result", "An existing file is uploading. Please wait."))
        }
    }

    @Transactional(rollbackFor = [SQLOperationException::class])
    @Throws(IOException::class)
    fun readCSVData(file: File?): String {
        val inputStream = DataInputStream(FileInputStream(file))
        val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
        var row: String
        reader.readLine()
        var rowNo = 1
        uploadedRecordsAmount = 0
        uploadStopped = false
        while (reader.readLine().also { row = it } != null) {
            uploadedRecordsAmount++
            rowNo++
            println(row)
            val rowItems = row.split(Regex(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).toTypedArray()
            if (rowItems.size < 8) {
                break
            }
            val invoice = InvoiceEntity(null, rowItems[0], rowItems[1], rowItems[2], rowItems[3].toInt(),
                    rowItems[4], rowItems[5].toDouble(), rowItems[6], rowItems[7])
            try {
                invoiceService.saveInvoice(invoice)
            } catch (e: Exception) {
                e.printStackTrace()
                uploadedRecordsAmount = 0
                uploadStopped = true
                return "Row $rowNo has invalid data."
            }
        }
        if (rowNo <= 1) {
            return "Empty file"
        }
        uploadStopped = true
        return (rowNo - 1).toString() + " new records uploaded successfully"
    }
}