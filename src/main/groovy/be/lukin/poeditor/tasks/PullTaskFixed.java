package be.lukin.poeditor.tasks;

import be.lukin.poeditor.Config;
import be.lukin.poeditor.FileTypeEnum;
import be.lukin.poeditor.FilterByEnum;
import be.lukin.poeditor.models.Project;

import java.io.File;
import java.util.Arrays;

public final class PullTaskFixed extends BaseTask {

    @Override
    public void handle() {
        System.out.println("Downloading translations");
        Config config = super.config;
        Project details = client.getProject(config.getProjectId());

        System.out.println("Project: " + details.name + " (id:" + details.id + ", type:" + config.getType() + ")");
        FileTypeEnum fte = FileTypeEnum.valueOf(config.getType().toUpperCase());

        for (String languageKey : config.getLanguageKeys()) {
            String path = config.getLanguage(languageKey);
            File exportFile = new File(System.getProperty("user.dir"), path);
            exportFile.getParentFile().mkdirs();
            FilterByEnum[] filters = config.getFilters(languageKey);

            File f = client.export(config.getProjectId(), languageKey, fte, filters, exportFile, config.getTagsPull());
            System.out.println(" - Trans " + languageKey + ": " + path);
            System.out.println(" - Filters " + languageKey + ": " + Arrays.toString(filters));
        }
    }
}
