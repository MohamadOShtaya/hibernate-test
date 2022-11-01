package org.hibernate.app;


import org.hibernate.model.Question;
import org.hibernate.model.FlashCard;
import org.hibernate.model.Subject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.*;



public class Main {


    public static void main(String[] args) throws SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Starting Transaction");
        //**************************open web browser

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        ProfilesIni settings = new ProfilesIni();
        FirefoxProfile profile1 = settings.getProfile("default");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile1);
        driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");
        //*************************
        List<FlashCard> listOfflash = new ArrayList<>();
        List<String> linkList = new ArrayList<>();
        int qcount = 0;
        List<Question> questionsList = new ArrayList<>();

        //******************************* get all subjects

        List<WebElement> listSubElm = driver.findElements(By.xpath("//*[@id=\"bd-sidebar\"]/ul/li/a"));
        List<Subject> listOfsub = new ArrayList<>();
        int count = 0;
        int quick = 1;
        int questionCount = 0;
        for (WebElement obj : listSubElm) {
            count++;
            listOfsub.add(new Subject(count, obj.getText(), null, obj.getAttribute("href")));
            if (count == 1) {
                System.out.println(listOfsub.get(0).toString());
                entityManager.getTransaction().begin();
                String name = listOfsub.get(0).getName();
                String url = listOfsub.get(0).getUrl();
                Subject subject = new Subject(name, url);
                entityManager.persist(subject);
                entityManager.getTransaction().commit();
            }
        }
        for (int i = 0; i < listOfsub.size(); i++) {
            driver.get(listOfsub.get(i).getUrl());
            //****************************************handle button next
            //  listOfsub.get(i).setFlashCards(flashList);
            String str = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[2]")).getText();
            String[] arr = str.split(" ");
            int firstNumber = Integer.parseInt(arr[0]);
            int Qcount = firstNumber;
            int lastNumber = Integer.parseInt(arr[2]);
            //*****************************************saving flash card

            while (quick < lastNumber) {
                List<WebElement> listQusElm = driver.findElements(By.xpath("/html/body/div/main/div[3]/div/div[3]/div/a"));
                WebElement btnNext = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[1]"));
                for (WebElement obj : listQusElm) {
                    qcount++;
                    linkList.add(obj.getAttribute("href"));
                    listOfflash.add(new FlashCard(obj.getText(), null, obj.getAttribute("href")));
                }
                listOfsub.get(i).setFlashCards(listOfflash);
                btnNext.click();
                quick++;
                driver.getCurrentUrl();
                driver.get(driver.getCurrentUrl());

            }

        }


        for (int j = 0; j < listOfsub.size(); j++) {
            driver.get(listOfsub.get(j).getUrl());
            for (int k = 0; k < listOfflash.size(); k++) {
                driver.get(listOfflash.get(k).getUrl());
                List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));

                for (WebElement e : questionElements) {
                    questionCount++;
                    questionsList.add(new Question(questionCount, e.getText()));
                }
                questionsList.remove(0);
                questionsList.remove(1);
                listOfflash.get(k).setqList(questionsList);
                //Try catch
                //  System.out.println(listOfflash.toString());

            }

        }

    }
}



















































// System.out.println(listOfflash.toString());
      /*  for (String str1 : linkList) {
            driver.get(str1);
            List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));
            List<questions> questionsList=new ArrayList<>();
            try {

                System.out.println(questionElements.size());
            } catch (Exception e) {
                System.out.println("error");
            }
        }

       */










//        WebElement lastQuesWebElm = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[3]"));
//        List<WebElement> lastListQusElm = lastQuesWebElm.findElements(By.tagName("a"));
//        for(WebElement obj: lastListQusElm  ){
//            qcount++;
//            listOfQus.add(new questions(qcount, obj.getText()));
//        }
//        //System.out.println(listOfQus.toString());
//        //******************************************get last sub questions
//        //System.out.println("---------------------------------------------");
//        String [] arrQes = new String[listOfQus.size()];
//        for(int i=0;i<listOfQus.size();i++){
//            //System.out.println(listOfQus.get(i).getName());
//            arrQes [i] = Arrays.toString(listOfQus.get(i).getName().split("\\R"));
//            //System.out.println();
//        }
//       String ss = Arrays.toString(arrQes);
//        //System.out.println(ss);
//*************************************
//*[@id="bd-docs-nav"]////*[@id="bd-sidebar"]/ul//






