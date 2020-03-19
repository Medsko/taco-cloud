package com.medsko.tacos;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import static org.junit.jupiter.api.Assertions.fail;

class RandomBullshitTest {

	private final String testDirectory = "C:\\Users\\Medsko\\Downloads\\Frank Ocean - Endless (2018) Mp3 (320 kbps) [Hunter]\\Frank Ocean - Endless (2018)";

	@Test
	void testFileMetaData() {

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get(testDirectory))) {

			for (Path file : ds) {

				UserDefinedFileAttributeView fileAttributes = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
				fileAttributes.list().forEach(RandomBullshitTest::p);

				FileStore store = Files.getFileStore(file);
				p(store);
			}

		} catch (IOException ioex) {
			ioex.printStackTrace();
			fail();
		}
	}

	private static void p(Object obj) {
		System.out.println(obj);
	}

}
