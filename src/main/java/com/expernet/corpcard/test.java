package com.expernet.corpcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

public class test {

		public static int[] solution(String[] keymap, String[] targets) {
			int[] answer = {};
			return answer;
		}

	public static void main(String[] args) {

		String[] keymap = {"ABACD", "BCEFD"};
		String[] targets = {"ABCD", "AABBCCDD"};

		System.out.println(solution(keymap, targets));

	}

}
