package pl.kszucki.crmznsurance.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kszucki.crmznsurance.Entity.Attachment;
import pl.kszucki.crmznsurance.Model.ResponseData;
import pl.kszucki.crmznsurance.Service.AttachemntService;

@RestController
public class AttachmentController {

    /*        <API to download and upload, working well (checked in postman), however not compatible while
                                                        using thymeleaf>
    private final AttachemntService attachemntService;


    public AttachmentController(AttachemntService attachemntService){
        this.attachemntService = attachemntService;
    }
    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURL = "";
        attachment = attachemntService.saveAttachemnt(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/").path(attachment.getAttachmentId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),downloadURL,file.getContentType(),file.getSize());
   }

   @GetMapping("/download/{fileId}")
   public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachemntService.getAttachment(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"").body(new ByteArrayResource(attachment.getData()));
   }


 */

}
