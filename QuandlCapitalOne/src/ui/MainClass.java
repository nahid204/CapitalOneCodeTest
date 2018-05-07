package ui;


import data.DataAnalysis;
import data.SecurityCalculation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.*;

import java.io.IOException;
import java.util.Scanner;


public class MainClass {
    public static void main(String[] args)
    {
        System.out.println("Welcome to Quandl WIKI Stock Price API Middleware");
        Scanner choiceScanner = new Scanner(System.in);

        boolean continueFlag = true;


        while (continueFlag)
        {
            System.out.println(UIMenu.getUIMenu());
            int choice = choiceScanner.nextInt();

            System.out.println("Enter Security Codes: (For multiples use spaces) ");
            Scanner sc = new Scanner(System.in);
            try
            {

                String codes = sc.nextLine();
                System.out.println("Enter date range: ");
                System.out.println("Enter starting month: (yyyy-MM) ");
                String startMonth = sc.nextLine();
                DateMonth dateFrom = new DateMonth(startMonth);

                System.out.println("Enter ending month: (yyyy-MM) ");
                String endMonth = sc.nextLine();
                DateMonth dateTo = new DateMonth(endMonth);
                DataAnalysis analysis = new DataAnalysis(codes, dateFrom, dateTo);

                switch(choice)
                {
                    case 1:
                        System.out.println(analysis.getReport());
                        break;
                    case 2:
                        System.out.println(analysis.getBusyDayReport());
                        break;
                    case 3:
                        System.out.println(analysis.getBiggestLoserReport());
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InvalidDateMonthStringException e) {
                System.out.println(e.getMessage()+"Please provide date in (yyyy-MM) format");
            } catch (IOException e) {
                System.out.println("Connection I/O error");
            } catch (InvalidDateException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                System.out.println("Data Error");
            } catch (NoDataException e) {
                System.out.println("No Data Found");
            } catch (java.text.ParseException e) {
                System.out.println("Date Formatting error");
            }


            System.out.println("Continue? (Y/N):");
            String continueString = sc.next();
            continueFlag = !continueString.toUpperCase().startsWith("N");
        }
//        try {
//            SecurityCalculation sec = new SecurityCalculation("MSFT", new DateMonth(2017,1), new DateMonth(2017,3));
//            System.out.println(sec);
//            System.out.println(sec.getBusyDaysReport());
//            System.out.println(sec.getLosingDaysReport());
//        } catch (InvalidDateException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoDataException e) {
//            e.printStackTrace();
//        }
    }

}
