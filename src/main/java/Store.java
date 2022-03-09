import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
public class Store{
    public static final int storeSize = 10; //задаем размер склада
    public static String[] store = new String[storeSize];//создаем объект склада
    public static int counterUnload = 1;

    /*Свободное место на складе*/
    public static int getFreeSpaces() {
        int countFreeSpaces = 0;
        for (int i = 0; i < store.length; i++) {
            if (store[i] == null) {
                countFreeSpaces++;
            }
        }
        return countFreeSpaces;
    }
        /*Добавление груза*/
    public static void addProduct(String object) {
        if (getFreeSpaces() > 0) {
            for (int i = 0; i < store.length; i++) {
                if (store[i] == null) {
                    store[i] = object;
                    break;
                }
            }
            System.out.println("Ваш груз успешно добавлен на склад!");
        } else {
            System.out.println("Склад перегружен!!! Нужно что-то убрать со склада");
        }
    }
    /*Удаление груза*/
    public static void delProduct(String object) {
        if (getFreeSpaces() < storeSize) {
            for (int i = 0; i < store.length-getFreeSpaces(); i++) {
                if (store[i].equals(object)) {
                    store[i] = null;
                    continue;
                }else if (store[i] == null ||(!store[i].equals(object))){ //чтобы не напороться на null,и не получить NPE
                    System.out.println("К сожалению у нас нет такого груза на хранении, попробуйте еще раз");
                    continue;
                }
            }
            System.out.println("Ваш груз успешно удален со склада!");
        } else {
            System.out.println("Склад полностью свободен, сначала что-нибудь добавьте на склад!");
        }
    }
    /*Проверка наличия груза*/
    public static void checkProduct(String object) {
        boolean checkFlag = false;
        if (getFreeSpaces() != 0) {
            for (int i = 0; i < store.length; i++) {
                if (store[i] == object) {
                    break;
                }
            }
            System.out.println("Такой груз есть на складе!");
        } else {
            System.out.println("Склад полностью свободен, сначала что-нибудь добавьте на склад!");
        }

    }
    /*Количество грузов на складе*/
    public static void numberCheckProducts(String object) {
        int counter = 0;
        for (int i = 0; i < store.length; i++) {
            if ((store[i] != null) && store[i].equals(object)) {
                counter += 1;
            }
        }
        System.out.println("Таких позиций на складе " + counter);

    }
    /*Выгрузка в Excel*/
    public static void unloadToExcel() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("StoreUnLoad");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("В наличии на складе");
        FileOutputStream fos = new FileOutputStream("Store.xls");
        if (getFreeSpaces() != 0){
            for (int i = 0; i < store.length-getFreeSpaces();i++){
                row.createCell(counterUnload).setCellValue(store[i]);
                counterUnload++;
            }
        }else{
            System.out.println("Выгрузка не возможна!!! Сначала сдайте груз на хранеие");
        }

        wb.write(fos);
        fos.close();
    }














}
