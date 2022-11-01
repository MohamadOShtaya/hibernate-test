package org.hibernate.app;


import org.hibernate.repo.Repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;


public class Main {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistence");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static Repo fileRepository;
    public static void main(String[] args) throws SQLException {

        entityManager.getTransaction().begin();
        fileRepository=new Repo(entityManager);
        fileRepository.saveSubject();
        fileRepository.getAllQuestions();
        entityManager.getTransaction().commit();
        entityManager.close();


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






