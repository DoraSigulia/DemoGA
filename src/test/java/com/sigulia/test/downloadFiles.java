package com.sigulia.test;
import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byPartialLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class downloadFiles {

    ClassLoader classLoader = getClass().getClassLoader();
    String  pdfFile = "junit-user-guide-5.8.2.pdf",
            xlsFile = "prajs_ot_27042022.xls",
            csvFile = "username.csv",
            zipFile = "anyFileZip.zip";


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Работа с файлами в старой Java")
    public void downloadFileOldJava() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File anyFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(anyFile)) {
            assertThat(new String(is.readAllBytes(), UTF_8)
                    .contains("This repository is the home of the next generation of JUnit"));
        }
    }

    @Test
    @DisplayName("Работа с файлами в новой Java")
    public void downloadFileNewJava() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File anyFile = $("#raw-url").download();
        String readAnyFile = Files.readString(anyFile.toPath(), UTF_8);
        assertThat(readAnyFile.contains("This repository is the home of the next generation of JUnit"));
    }

    @Test
    @DisplayName("Добавление файла")
    public void uploadFile() {
        open("https://demoqa.com/upload-download");
        $("input[type='file']")
                .uploadFromClasspath("README.md");
        $("#uploadedFilePath").shouldHave(text("README.md"));
    }

    @Test
    @DisplayName("Работа с pdf файлами")
    public void downloadPdf() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfFile = $(byText("PDF download")).download();
        PDF file = new PDF(pdfFile);
        assertThat(file.author).contains("Stefan Bechtold");
        assertThat(file.text).contains("JUnit 5 User Guide");
    }

    @Test
    @DisplayName("Работа с xls файлами")
    public void downloadXls() throws Exception {
        open("http://romashka2008.ru/price");
        File xlsFile = $(".site-main").$("p").$(byText("Скачать Прайс-лист Excel")).download();
        XLS fileXls = new XLS(xlsFile);
        assertThat(fileXls.excel
                .getSheetAt(0)
                .getRow(21)
                .getCell(2)
                .getStringCellValue())
                .contains("БУМАГА ДЛЯ ОФИСНОЙ ТЕХНИКИ");
    }

    @Test
    @DisplayName("Работа с csv файлами из интернета")
    public void csvDownload() throws Exception {
        open("https://support.staffbase.com/hc/en-us/articles/360007108391#csv-example-username");
        File file = $(byPartialLinkText("Download: CSV File with the Minimum Data Set for Username Onboarding")).download();
        try (InputStream is = new FileInputStream(file);
            CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = csvReader.readAll();
            assertThat(content.get(0)).contains("Username; Identifier;First name;Last name");
        }
    }


    @Test
    @DisplayName("Работа с csv файлами из resources")
    public void csvClassPath() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("email.csv");
            CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = csvReader.readAll();
            assertThat(content.get(0)).contains("Login email;Identifier;First name;Last name");
            }
    }



    @Test
    @DisplayName("Работа с zip файлами")
    void zipTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/" + zipFile));
        ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream(zipFile)));
        ZipEntry entry;
        while((entry = is.getNextEntry()) != null) {
            if (entry.getName().equals(pdfFile)) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    assert inputStream != null;
                    PDF pdf = new PDF(inputStream);
                    assertThat(pdf.author).contains("Stefan Bechtold");
                    assertThat(pdf.text).contains("JUnit 5 User Guide");
                    assertThat(pdf.numberOfPages).isEqualTo(166);
                }
            } else if (entry.getName().equals(csvFile)) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    assert inputStream != null;
                    try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = csvReader.readAll();
                        assertThat(content.get(0)).contains("Username; Identifier;First name;Last name");
                    }
                }
            } else if (entry.getName().equals(xlsFile)) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    assert inputStream != null;
                    XLS fileXls = new XLS(inputStream);
                    assertThat(fileXls.excel
                            .getSheetAt(0)
                            .getRow(21)
                            .getCell(2)
                            .getStringCellValue())
                            .contains("БУМАГА ДЛЯ ОФИСНОЙ ТЕХНИКИ");
                }
            }
        }
    }
}


