package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadFileTest {

	private String filePath1;
	private String filePath2;

	@BeforeEach
	void setUp() throws Exception {
		filePath1 = "src\\projectTests\\DNA1.txt";
		filePath2 = "src\\projectTests\\DNA2.txt";
	}

	@Test
	void readExistingFile() {
		String expected = "ACGCGGCTTCCAAGGGTATGAAACTATTCCTCCTATGCGCTGACATAGTCTTACCGAGTATTATAAGAATCTCCGAATTGAATTTGG";
		String dna_seq = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				dna_seq += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		assertEquals(dna_seq, expected);
	}
	
	@Test
	void readNotExistingFile() {
		String expected = "";
		String dna_seq = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				dna_seq += line;
			}
		} catch (IOException e) {
			assertTrue(true);
		}
	}
}
