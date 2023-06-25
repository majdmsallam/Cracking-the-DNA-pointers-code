package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Utils;

class CountOccoruncesTest {
	private String DNA_seq;
	private String pointer;
	@BeforeEach
	void setUp() throws Exception {
		DNA_seq="ACGCGGCTTCCAAGGGTATGAAACTATTCCTCCTATGCGCTGACATAGTCTTACCGAGTATTATAAGAATCTCCGAATTGAATTTGG";
		pointer="GG";
	}

	@Test
	void test() {
		int expected=3;
		int result=Utils.countOccurrences(DNA_seq, pointer);
		assertEquals(expected, result);
	}

}
