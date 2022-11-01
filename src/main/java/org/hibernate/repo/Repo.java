package org.hibernate.repo;

import org.hibernate.HibernateException;
import org.hibernate.model.FlashCard;
import org.hibernate.model.Question;
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
import java.util.ArrayList;
import java.util.List;

public class Repo  implements  RepoInterface{

    private EntityManager entityManager;
    List<FlashCard> listOfflash = new ArrayList<>();
    List<String> linkList = new ArrayList<>();
    int qcount = 0;
    List<Question> questionsList = new ArrayList<>();
    List<Subject> listOfsub = new ArrayList<>();
    int count = 0;
    int quick = 1;
    int questionCount = 0;
    int myCount =1;
    int myQuestionCount =1;
    List<Question> tempQuestionsList = new ArrayList<>();
    int tempQuestionsListcount=0;
    public Repo(EntityManager entityManager) {

        this.entityManager = entityManager;
    }
    public void saveSubject() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();

            ProfilesIni settings = new ProfilesIni();
            FirefoxProfile profile1 = settings.getProfile("default");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setProfile(profile1);
            driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");

            List<WebElement> listSubElm = driver.findElements(By.xpath("//*[@id=\"bd-sidebar\"]/ul/li/a"));

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
                        myCount++;
                        if(myCount==6){
                            for(int j=0;j<listOfflash.size();j++){
                                entityManager.getTransaction().begin();
                                String name = listOfflash.get(j).getName();
                                String url = listOfflash.get(j).getUrl();
                                int subjectId=1;
                                FlashCard fc = new FlashCard(name,null,url,listOfsub.get(j),subjectId);
                                entityManager.persist(fc);
                                entityManager.getTransaction().commit();
                            }

                        }
                    }
                    listOfsub.get(i).setFlashCards(listOfflash);
                    btnNext.click();
                    quick++;
                    driver.getCurrentUrl();
                    driver.get(driver.getCurrentUrl());

                }

            }
            //List<Subject> fileList = entityManager.createQuery("select f from file f where f.uploaded=0 and f.downloaded=1 and f.parsed=1", Subject.class).getResultList();

        }catch (HibernateException e)
        {
            e.printStackTrace();
        }

    }
    public void getAllQuestions(){
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();

            ProfilesIni settings = new ProfilesIni();
            FirefoxProfile profile1 = settings.getProfile("default");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setProfile(profile1);
            driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");

            for (int j = 0; j < listOfsub.size(); j++) {
                driver.get(listOfsub.get(j).getUrl());
                for (int k = 0; k < listOfflash.size(); k++) {
                    driver.get(listOfflash.get(k).getUrl());
                    List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));

                    for (WebElement e : questionElements) {
                        questionCount++;
                        myQuestionCount++;
                        questionsList.add(new Question(questionCount, e.getText()));
                    }
                    questionsList.remove(0);
                    questionsList.remove(1);
                    listOfflash.get(k).setqList(questionsList);
                    if(myQuestionCount <=12){
                        String name = questionsList.get(k).getName();
                        Question ques = new Question(name,listOfflash.get(k));
                        entityManager.getTransaction().begin();
                        entityManager.persist(ques);
                        entityManager.getTransaction().commit();
                    }
                }

            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }


}
