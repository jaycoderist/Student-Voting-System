import java.io.FileOutputStream;
import java.io.File;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportService {

    public void exportElectionReport() {

        // ✅ SAVE FILE INSIDE PROJECT FOLDER (VISIBLE)
        String filePath = "Election_Report.xlsx";

        System.out.println("Exporting to: " + filePath);

        try (Connection conn = DBConnection.getConnection();
             Workbook workbook = new XSSFWorkbook()) {

        
            Sheet studentSheet = workbook.createSheet("Students_Voted");

            Row header1 = studentSheet.createRow(0);
            header1.createCell(0).setCellValue("Student Number");
            header1.createCell(1).setCellValue("Candidate ID");
            header1.createCell(2).setCellValue("Date");

            String sql1 = "SELECT student_number, candidate_id, vote_date FROM votes";
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);

            int rowNum = 1;
            while (rs1.next()) {
                Row row = studentSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs1.getString("student_number"));
                row.createCell(1).setCellValue(rs1.getInt("candidate_id"));
                row.createCell(2).setCellValue(rs1.getString("vote_date"));
            }

            Sheet resultSheet = workbook.createSheet("Results");

            Row header2 = resultSheet.createRow(0);
            header2.createCell(0).setCellValue("Candidate Name");
            header2.createCell(1).setCellValue("Position");
            header2.createCell(2).setCellValue("Total Votes");

            String sql2 = "SELECT name, position, vote_count FROM candidates ORDER BY vote_count DESC";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);

            int rowNum2 = 1;
            while (rs2.next()) {
                Row row = resultSheet.createRow(rowNum2++);
                row.createCell(0).setCellValue(rs2.getString("name"));
                row.createCell(1).setCellValue(rs2.getString("position"));
                row.createCell(2).setCellValue(rs2.getInt("vote_count"));
            }

         
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);

            fileOut.close();
            workbook.close(); 

            System.out.println("Excel exported successfully!");

        } catch (Exception e) {
            System.out.println("Export failed!");
            e.printStackTrace();
        }
    }
}