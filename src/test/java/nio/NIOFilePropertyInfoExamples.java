package nio;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * NIO 에서 API 파일 경로를 지정하기 위해 PATH 사용
 * - 절대경로, 상대경로 모두 사용 가능
 * */
class PathExample {
    Path path = Paths.get("a", "b", "c");
    Path path2 = Paths.get(URI.create("url"));

}

/**
 * 파일과 디렉토리의 생성, 삭제, 정보 읽는 메서드들을 제공함
 * */
class FileSystemExample {
    FileSystem fs = FileSystems.getDefault();
}


