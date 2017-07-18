package be.lukin.poeditor.tasks;

import be.lukin.poeditor.models.UploadDetails;

import java.io.File;

public class PushTermsTaskFixed extends BaseTask {
    
    @Override
    public void handle() {
        System.out.println("Pushing terms");

        if(config.getTerms() != null) {
            File termsFile = new File(System.getProperty("user.dir"), config.getTerms());
            UploadDetails details = client.uploadTerms(config.getProjectId(), termsFile, config.getTagsAll(), config.getTagsNew(), config.getTagsObsolete());
            System.out.println("Synced: " + details);
        } else {
            System.out.println("No terms defined");
        }   
    }
}
