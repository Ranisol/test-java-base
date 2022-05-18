package io.io_stream;

import org.junit.jupiter.api.Test;

import java.io.*;


class FileInputStreamExample {
    private String resourcePath = getClass().getClassLoader().getResource("output.txt").getPath();

    void createFileInputStream() throws FileNotFoundException {
        // 첫번째 방법
        FileInputStream fis = new FileInputStream(resourcePath);

        // 두번째 방법
        File file = new File("out/test/resources/output.txt");
        FileInputStream fis2 = new FileInputStream(file);

        // 읽는 방법은 InputStream 과 동일
    }
}


class FileOutputStreamExample {
    private String resourcePath = getClass().getClassLoader().getResource("output.txt").getPath();

    void createFileOutputStream() throws FileNotFoundException {
        // 첫번째 방법
        FileOutputStream fos = new FileOutputStream(resourcePath);

        // 두번째 방법
        File file = new File("out/test/resources/output.txt");
        FileOutputStream fos2 = new FileOutputStream(file);

        // 쓰는 방법은 OutputStream 과 동일
    }
}

class FileClassExample {
    private String resourcePath = "out/test/resources";

    @Test
    void createAndDeleteFileOrDirectory() {

        File testDir = new File(resourcePath + "/test");
        System.out.println(testDir.getAbsolutePath());
        boolean mkdirResult = testDir.mkdir();
        testDir.exists();
        // testDir.mkdirs(); // 경로상에 폴더가 없더라도 마지막 지정한 폴더까지 생성

        File testFile = new File(resourcePath + "/test/text.txt");
        try {
            System.out.println(testFile.getAbsolutePath());
            testFile.createNewFile();
        }catch (IOException e) {
            e.printStackTrace();
        }


        testFile.delete(); // 파일 또는 디렉토리 삭제
        testDir.delete(); // 하위 디렉토리가 있으면 삭제 x
    }

    @Test
    void checkInfoOfFileOrDirectory() {
        File testDir = new File("src/test/resources/test");
        testDir.mkdirs();

        boolean canExecute = testDir.canExecute(); // 실행할 수 있는 파일인지 확인
        System.out.println("canExecute " + canExecute);

        boolean canRead = testDir.canRead(); // 읽을 수 있는 파일인지
        System.out.println("canRead " + canRead);

        boolean canWrite = testDir.canWrite(); // 수정 및 저장할 수 있는 파일인지
        System.out.println("canWrite " + canWrite);

        String getName = testDir.getName(); // 파일 이름 리턴
        System.out.println("getName " + getName);

        String getParent = testDir.getParent(); // 부모 파일 이름 리턴
        System.out.println("getParent " + getParent);

        String path = testDir.getPath();
        System.out.println("path " + path);

        boolean isDirectory = testDir.isDirectory();
        System.out.println("isDirectory " + isDirectory);

        boolean isFile = testDir.isFile();
        System.out.println("isFile " + isFile);

        boolean isHidden = testDir.isHidden();
        System.out.println("isHidden " + isHidden);

        long lastModified = testDir.lastModified();
        System.out.println("lastModified " + lastModified);

        long length = testDir.length(); // 파일 크기
        System.out.println("length " + length);

        File[] listFiles = testDir.listFiles();


    }
}
