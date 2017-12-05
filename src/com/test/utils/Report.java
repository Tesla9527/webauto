package com.test.utils;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Report {
    private static String testLogPath, screenshotpath, reportname;
    private static String headingColor, settingColor, bodyColor;
    private static boolean takeScreenshotFailedStep, takeScreenshotPassedStep;
    private static int stepno;
    private static int pass_no;
    private static int fail_no;
    private static LocalDateTime start_time;
    private static LocalDateTime end_time;
    private static String functioncontent;
    private static int function_no;

    public enum Status {
        PASS, FAIL, DONE
    }

    public static void setstepno(int newstepno) {
        stepno = newstepno;
    }

    public static int getstepno() {
        return stepno;
    }

    public static void setpassno(int newpassno) {
        pass_no = newpassno;
    }

    public static int getpassno() {
        return pass_no;
    }

    public static void setfailno(int newfailno) {
        fail_no = newfailno;
    }

    public static int getfailno() {
        return fail_no;
    }

    public static void setstarttime(LocalDateTime newstarttime) {
        start_time = newstarttime;
    }

    public static LocalDateTime getstarttime() {
        return start_time;
    }

    public static void setendtime(LocalDateTime newendtime) {
        end_time = newendtime;
    }

    public static LocalDateTime getendtime() {
        return end_time;
    }

    public static void InitialReport(String reportPath, String ReportName, boolean TakeScreenshotFailedStep, boolean TakeScreenshotPassedStep) {
        reportname = ReportName;
        CreateIfMissing(reportPath);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        testLogPath = reportPath + reportname + "_" + df.format(new Date()) + ".html";

        screenshotpath = reportPath + "\\Screenshots\\";
        CreateIfMissing(screenshotpath);

        headingColor = "#687C7D";
        settingColor = "#C6D0D1";
        bodyColor = "#EDEEF0";

        takeScreenshotFailedStep = TakeScreenshotFailedStep;
        takeScreenshotPassedStep = TakeScreenshotPassedStep;
    }

    private static void CreateIfMissing(String path) {
        File theDir = new File(path);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    public static String getreportname() {
        return reportname;
    }

    public static void CreateTestLogHeader(String projectName) {
        String testLogHeader;
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");//设置时间格式
        testLogHeader = "<html>" +
                "<head>" +
                "<title>" +
                projectName + " - " + reportname + " Automation Execution Results" +
                "</title>" +

                "<script>" +

                "function toggleMenu(objID) {  if (!document.getElementById) return; 	 var ob = document.getElementById(objID).style;  if(ob.display === 'none') { 					 try {  ob.display='table-row-group';  } catch(ex) {						 ob.display='block';  }  }  else {  ob.display='none';  }  } " +
                "function toggleSubMenu(objId) { for(i=1; i<10000; i++) { 	 var ob = document.getElementById(objId.concat(i));  if(ob === null) {  break;  }  if(ob.style.display === 'none') {  try { ob.style.display='table-row'; } catch(ex) { ob.style.display='block'; } } else { ob.style.display='none';  }}}" +

                "</script>" +


                "</head>" +

                "<body>" +
                "<p align='center'>" +
                "<table border='1' bordercolor='#000000' bordercolorlight='#FFFFFF' cellspacing='0' id='table1' width='1000' height='100'>" +
                "<tr bgcolor='" + headingColor + "'>" +
                "<td colspan='5'>" +
                "<p align='center'><font color='" + bodyColor + "' size='4' face='Copperplate Gothic Bold'>" +
                projectName + " - " + reportname + " Automation Execution Results" +
                "</font></p>" +
                "</td>" +
                "</tr>" +

                "<tr bgcolor='" + settingColor + "'>" +
                "<td colspan='3'>" +
                "<p align='justify'><b><font color='" + headingColor + "' size='2' face='Verdana'>" +
                "DATE: " + df1.format(new Date()) +
                "</p></font></b>" +
                "</td>" +
                "<td colspan='2'>" +
                "<p align='justify'><b><font color='" + headingColor + "' size='2' face='Verdana'>" +
                "Time: " + df2.format(new Date()) +
                "</p></font></b>" +
                "</td>" +
                "</tr>" +


                "<tr bgcolor='" + headingColor + "'>" +
                "<td><b><font color='" + bodyColor + "' size='2' face='Verdana'>Step No</font></b></td>" +
                "<td><b><font color='" + bodyColor + "' size='2' face='Verdana'>Step Name</font></b></td>" +
                "<td><b><font color='" + bodyColor + "' size='2' face='Verdana'>Description</font></b></td>" +
                "<td><b><font color='" + bodyColor + "' size='2' face='Verdana'>Status</font></b></td>" +
                "<td><b><font color='" + bodyColor + "' size='2' face='Verdana'>Time</font></b></td>" +
                "</tr>";
        FileWriter fw = null;
        try {
            fw = new FileWriter(testLogPath, false);
            fw.write(testLogHeader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void UpdateTestLogTitle(String currentfunctionname) {
        functioncontent = currentfunctionname;
        function_no = 0;
        String testStepRow = "<tr class='subheading subsection'>" +
                "<td colspan='5' size='2' face='Verdana'onclick=\"toggleSubMenu('" + functioncontent +
                "')\">" +
                "<font size='2' face='Verdana'>&nbsp;+ " + functioncontent + "</font>" +
                "</td>" +
                "</tr>";
        FileWriter fw = null;
        try {
            fw = new FileWriter(testLogPath, true);
            fw.write(testStepRow);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void UpdateTestLog(String stepName, String stepDescription, Status stepStatus) {
        String screenShotName;
        stepno = stepno + 1;
        function_no = function_no + 1;
        String testStepRow = "<tr bgcolor='" + bodyColor + "' class='content' id='" + functioncontent + function_no + "'>" +
                "<td>" +
                "<font size='2' face='Verdana'>" + stepno + "</font>" +
                "</td>" +
                "<td>" +
                "<font size='2' face='Verdana'>" + stepName + "</font>" +
                "</td>" +
                "<td>" +
                "<font size='2' face='Verdana'>" + stepDescription + "</font>" +
                "</td>";

        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");//设置截图日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置步骤日期格式

        if (stepStatus.equals(Status.FAIL)) {
            fail_no = fail_no + 1;
            if (takeScreenshotFailedStep) {
                screenShotName = functioncontent + "_Fail_" + df1.format(new Date());
                ScreenshotHelper.takeScreenshot(screenshotpath + screenShotName + ".png");

                testStepRow += "<td>" +
                        "<a href='Screenshots\\" + screenShotName + ".png" + "' target='about_blank'>" +
                        "<font color='red' size='2' face='Verdana'><b>" + stepStatus + "</b></font>" +
                        "</a>" +
                        "</td>";
            } else {
                testStepRow += "<td>" +
                        "<font color='red' size='2' face='Verdana'><b>" + stepStatus + "</b></font>" +
                        "</td>";
            }
        } else if (stepStatus.equals(Status.PASS)) {
            pass_no = pass_no + 1;
            if (takeScreenshotPassedStep) {
                screenShotName = functioncontent + "_Pass_" + df1.format(new Date());
                ScreenshotHelper.takeScreenshot(screenshotpath + screenShotName + ".png");
                testStepRow += "<td>" +
                        "<a href='Screenshots\\" + screenShotName + ".png" + "' target='about_blank'>" +
                        "<font color='green' size='2' face='Verdana'><b>" + stepStatus + "</b></font>" +
                        "</a>" +
                        "</td>";
            } else {
                testStepRow += "<td>" +
                        "<font color='green' size='2' face='Verdana'><b>" + stepStatus + "</b></font>" +
                        "</td>";
            }
        } else {
            testStepRow += "<td>" +
                    "<font size='2' face='Verdana'><b>" + stepStatus + "</b></font>" +
                    "</td>";
        }

        testStepRow += "<td>" +
                "<font size='2' face='Verdana'>" + df2.format(new Date()) + "</font>" +

                "</td>" +
                "</tr>";

        FileWriter fw = null;
        try {
            fw = new FileWriter(testLogPath, true);
            fw.write(testStepRow);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void CreateTestLogFooter(String executionTime, int nStepsPassed, int nStepsFailed) {
        String testLogFooter = "<tr bgcolor='" + settingColor + "'>" +
                "<td colspan='5'>" +
                "<center><b><font color='" + headingColor + "' size='2' face='Verdana'>Execution Duration: " + executionTime + "</font></b></center>" +
                "</td>" +
                "</tr>" +
                "<tr bgcolor='" + settingColor + "'>" +
                "<td colspan='3'>" +
                "<b><font color='green' face='Verdana'>PASS: " + nStepsPassed + "</b></font>" +
                "</td>" +
                "<td colspan=2>" +
                "<b><font color='red' face='Verdana'>FAIL: " + nStepsFailed + "</b></font>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</body>" +
                "</html>";
        FileWriter fw = null;
        try {
            fw = new FileWriter(testLogPath, true);
            fw.write(testLogFooter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
