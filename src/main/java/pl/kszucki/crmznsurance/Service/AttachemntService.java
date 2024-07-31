package pl.kszucki.crmznsurance.Service;

import org.springframework.web.multipart.MultipartFile;
import pl.kszucki.crmznsurance.Entity.Attachment;

public interface AttachemntService {
    Attachment saveAttachemnt(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
