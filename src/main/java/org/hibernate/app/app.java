package org.hibernate.app;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.model.Question;
import org.hibernate.model.flashCard;
import org.hibernate.model.subjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.sql.SQLException;
import java.util.*;



public class app {

    public static void main( String[] args ) throws SQLException {

           /* EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            System.out.println("Connected");

            System.out.println("Not connnect");

            */





        //**************************open web browser

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        ProfilesIni settings = new ProfilesIni();
        FirefoxProfile profile1=  settings.getProfile("default");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile1);
        driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");
        //*[@id="bd-sidebar"]/ul/li[1]/a
        // print code





        //***********************






        //******************************* get all subjects
        List<WebElement> listSubElm = driver.findElements(By.xpath("//*[@id=\"bd-sidebar\"]/ul/li/a"));
        List<subjects> listOfsub = new ArrayList<>();
        int count = 0;
        int quick = 1;
        List<flashCard> listOfflash = new ArrayList<>();
        List<String> linkList = new ArrayList<>();
        int qcount = 0;
        List<Question> questionsList = new ArrayList<>();
        int questionCount = 0;

        for (WebElement obj : listSubElm) {

            count++;
            listOfsub.add(new subjects(count, obj.getText(), null, obj.getAttribute("href")));
        }
       // System.out.println(listOfsub.get(0).toString());

        List<flashCard> flashList = new ArrayList<>();
        int flashCount=0;
        for (int i = 0; i < listOfsub.size(); i++) {

            driver.get(listOfsub.get(i).getUrl());
            // System.out.println(listOfsub.get(i).getUrl());
            List<WebElement> subjectelm = driver.findElements(By.xpath("/html/body/div/main/div[3]/div/div[3]/div/a"));
            for (WebElement obj : subjectelm) {
                flashCount++;
                flashList.add(new flashCard(flashCount, obj.getText(), null, obj.getAttribute("href")));
            }


            listOfsub.get(i).setFlashCards(flashList);

            //****************************************handle button next
            String str = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[2]")).getText();
            String[] arr = str.split(" ");
            int firstNumber = Integer.parseInt(arr[0]);
            int Qcount = firstNumber;
            int lastNumber = Integer.parseInt(arr[2]);
            //*****************************************


            while (quick <lastNumber) {
                List<WebElement> listQusElm = driver.findElements(By.xpath("/html/body/div/main/div[3]/div/div[3]/div/a"));
                WebElement btnNext = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[1]"));
                for (WebElement obj : listQusElm) {
                    qcount++;
                    linkList.add(obj.getAttribute("href"));
                    listOfflash.add(new flashCard(qcount, obj.getText(), null, obj.getAttribute("href")));
                }
                listOfsub.get(i).setFlashCards(listOfflash);

                btnNext.click();
                quick++;
                driver.getCurrentUrl();
               // System.out.println(driver.getCurrentUrl());
                driver.get(driver.getCurrentUrl());
               // System.out.println(listOfsub.get(i).getName());
                //System.out.println(listOfsub.get(i).toString());
                //System.out.println("**********************************************************************************");
            }


        }





        //******************************************saving flashCard objects
        for (int j = 0; j < listOfsub.size(); j++) {
            driver.get(listOfsub.get(j).getUrl());
            for (int i = 0; i < listOfflash.size(); i++) {
                driver.get(listOfflash.get(i).getUrl());
                List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));

                for (WebElement e : questionElements) {
                    questionCount++;
                    questionsList.add(new Question(questionCount, e.getText()));
                }
                questionsList.remove(0);
                questionsList.remove(1);
                listOfflash.get(i).setqList(questionsList);
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






