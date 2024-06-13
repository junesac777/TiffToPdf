import com.itextpdf.text.pdf.RandomAccessFileOrArray;
//Read Tiff File, Get number of Pages
import com.itextpdf.text.pdf.codec.TiffImage;
//We need the library below to write the final 
//PDF file which has our image converted to PDF
import java.io.FileOutputStream;
//The image class to extract separate images from Tiff image
import com.itextpdf.text.Image;
//PdfWriter object to write the PDF document
import com.itextpdf.text.pdf.PdfWriter;
//Document object to add logical image files to PDF
import com.itextpdf.text.Document;

public class App {
	public static void main(String args[]) {
		try {

			String filePath = "C://Users//sjun1//workspaces//17Workspace//TiffToPdf//src//main//resources//";
			String sourceFileName = "2.tif";
			String destFileName = "2.pdf";
			
			// Read the Tiff File
			RandomAccessFileOrArray myTiffFile = new RandomAccessFileOrArray(filePath + sourceFileName);
			// Find number of images in Tiff file
			int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);
			System.out.println("Number of Images in Tiff File" + numberOfPages);
			Document TifftoPDF = new Document();
			PdfWriter.getInstance(TifftoPDF, new FileOutputStream(filePath + destFileName));
			TifftoPDF.open();
			// Run a for loop to extract images from Tiff file
			// into a Image object and add to PDF recursively
			for (int i = 1; i <= numberOfPages; i++) {
				Image tempImage = TiffImage.getTiffImage(myTiffFile, i);
				TifftoPDF.add(tempImage);
			}
			TifftoPDF.close();
			System.out.println("Tiff to PDF Conversion in Java Completed");
		} catch (Exception i1) {
			i1.printStackTrace();
		}
	}
}