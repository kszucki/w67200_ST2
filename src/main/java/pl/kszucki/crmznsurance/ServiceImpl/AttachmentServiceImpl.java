package pl.kszucki.crmznsurance.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.kszucki.crmznsurance.Entity.Attachment;
import pl.kszucki.crmznsurance.Repository.AttachemntRepository;
import pl.kszucki.crmznsurance.Service.AttachemntService;
@Service
public class AttachmentServiceImpl implements AttachemntService {

    private AttachemntRepository attachemntRepository;

    public AttachmentServiceImpl(AttachemntRepository attachemntRepository){
        this.attachemntRepository = attachemntRepository;
    }

    @Override
    public Attachment saveAttachemnt(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw new Exception("File name contains invalid path sequence" + fileName);
            }

            Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return attachemntRepository.save(attachment);
        }catch (Exception e){
            throw new Exception("Could not save the file" + fileName);
        }

    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachemntRepository.findById(fileId).orElseThrow(
                () -> new Exception("file not found with Id: " + fileId));
    }
}
