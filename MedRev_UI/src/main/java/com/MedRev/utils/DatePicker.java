package com.MedRev.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.packages.common.WebDriverUtility;
import com.packages.common.WebElementUtility;

/**
 * 
 * @author Amarendra
 *
 */
public class DatePicker {

	private WebDriver driver;
	private WebElement calendarPopup;

	private WebElement txt_Date;
	private WebElement icon_Calendar;

	private String xpath_prevLink = "//div[@class='k-header']//a[contains(@class,'k-nav-prev')]";
	private String xpath_midLink = "//div[@class='k-header']//a[contains(@class,'k-nav-fast')]";
	private String xpath_nextLink = "//div[@class='k-header']//a[contains(@class,'k-nav-next')]";
	private String xpath_Month = "//table//tr[@role='row']/td[@role='gridcell']/a[normalize-space(.) = '%s']";
	private String xpath_Date = "//table//tr[@role='row']/td[not(contains(@class,'k-other-month'))][normalize-space(.) = '%s']";

	public DatePicker(WebDriver driver, WebElement calPopup) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.calendarPopup = calPopup;
	}

	public DatePicker(WebDriver driver, WebElement txtDate, WebElement iconCalendar, WebElement calPopup) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.txt_Date = txtDate;
		this.icon_Calendar = iconCalendar;
		this.calendarPopup = calPopup;
	}

	/**
	 * Selects date in opened calendar. Accepted date formats for
	 * {@code dateInYYYY_MM_DDFormat} are yyyy-mm-dd and yyyy/mm/dd
	 * 
	 * @param dateInYYYY_MM_DDFormat
	 */
	public void selectDate(String dateInYYYY_MM_DDFormat) {
		String[] arr = dateInYYYY_MM_DDFormat.split("-|/");
		String year = arr[0];
		String month = arr[1];
		String date = arr[2];

		selectDate(date, month, year);
	}

	public void selectDate(String dayStr,
			String monthStr, String yearStr) {
		int day = Integer.parseInt(dayStr);
		int month = Integer.parseInt(monthStr);
		int year = Integer.parseInt(yearStr);
		selectDateByJS(day, month, year);
	}

	public void selectDateByJS(int day,
			int month, int year) {

        String date = getFormattedDate(day, month, year);
        WebElementUtility.setValue(driver, txt_Date, date);
		Assert.assertEquals(txt_Date.getAttribute("value"), date, "Date not selected");
	}

	// TODO this is incomplete
	public void selectDate(int day,
			int month, int year) {
		WebDriverWait wait = WebDriverUtility.getWaitFor15Secs(driver);
		wait.until(ExpectedConditions.elementToBeClickable(icon_Calendar));
		icon_Calendar.click();
		WebDriverUtility.wait(1);

		WebElement prevLink = calendarPopup.findElement(By.xpath(xpath_prevLink));
		WebElement midLink = calendarPopup.findElement(By.xpath(xpath_midLink));
		WebElement nextLink = calendarPopup.findElement(By.xpath(xpath_nextLink));

		String midLinkTxt = midLink.getText();

		int yearDiff = year
				- Integer.parseInt(midLinkTxt.trim().split(" ")[1]);

		midLink.click();
		WebDriverUtility.wait(1);
		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					icon_Calendar.click();
					WebDriverUtility.wait(1);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					icon_Calendar.click();
					WebDriverUtility.wait(1);
					prevLink.click();
				}
			}
		}

		DateFormatSymbols symbols = new DateFormatSymbols();
	    String[] monthNames = symbols.getShortMonths();

		WebElement mnth = calendarPopup.findElement(By.xpath(String.format(
				xpath_Month, monthNames[month - 1])));
		icon_Calendar.click();
		WebDriverUtility.wait(1);
		mnth.click();
		WebDriverUtility.wait(1);

		WebElement dat = calendarPopup.findElement(By.xpath(String.format(
				xpath_Date, day)));
		icon_Calendar.click();
		WebDriverUtility.wait(1);
		dat.click();
		WebDriverUtility.wait(1);

		String date = getFormattedDate(day, month, year);
		Assert.assertEquals(txt_Date.getAttribute("value"), date, "Date not selected");
	}

	/**
	 * 
	 * @param day starts from 1
	 * @param month starts from 1
	 * @param year
	 * @return
	 */
	public String getFormattedDate(int day,
			int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        return date;
	}

	public static void main(String[] args) {
		String txt = "May 2016";
		System.out.println(txt.trim().split(" ")[1]);
	}
}
