package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Utils;

class ReverseComplementTest {
	private String DNA_seq;
	@BeforeEach
	void setUp() throws Exception {
		DNA_seq="CGCTGACATAGTCTTACCGAGTATTATAAG";
	}

	@Test
	void test() {
		String expented="CTTATAATACTCGGTAAGACTATGTCAGCG";
		String result=Utils.rev_dna_seq(DNA_seq);
		assertEquals(result, expented);
	}

}
