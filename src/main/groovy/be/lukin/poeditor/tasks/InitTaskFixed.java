package be.lukin.poeditor.tasks;

import be.lukin.poeditor.models.Project;
import be.lukin.poeditor.models.UploadDetails;

import java.io.File;

public class InitTaskFixed extends BaseTask {
    
    @Override
    public void handle() {
        System.out.println("Initializing");
        Project details = client.getProject(config.getProjectId());
        
        if(details != null){

            // Uploading terms
            if(config.getTerms() != null) {
                File termsFile = new File(System.getProperty("user.dir"), config.getTerms());
                UploadDetails ud = client.uploadTerms(config.getProjectId(), termsFile);
                System.out.println("- terms uploaded: " + ud);
            } else {
                System.out.println("- no terms defined");
            }

            // Create languages
            for(String lang : config.getLanguageKeys()){
                client.addProjectLanguage(config.getProjectId(), lang);
                System.out.println("- lang added: " + lang);
                File langFile = new File(System.getProperty("user.dir"), config.getLanguage(lang));
                client.uploadLanguage(config.getProjectId(), langFile, lang, true);
                System.out.println("- lang uploaded: " + lang);
            }
        } else {
            System.out.println("Project with id '" + config.getProjectId() + "' doesn't exist.");
        }
    }
}  
