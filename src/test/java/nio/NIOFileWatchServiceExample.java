package nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * 디렉토리 내부에서 파일 생성, 삭제, 수정등의 변화를 감지하는데 쓰임
 * */
class WatchServiceExample {
    void run() throws IOException, InterruptedException {
        // 파일 감시 객체 생성
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // 특정 경로에 파일 감시 시작
        Path path = Paths.get("/");
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

        // 감시이벤트 받아서 처리 (스레드 하나 할당해서 하는게 좋음)
        while (true) {
            WatchKey watchKey = watchService.take(); // 큐에 WatchKey 들어올때 까지 대기

            List<WatchEvent<?>> list = watchKey.pollEvents(); // watchEvent 얻어냄

            for(WatchEvent watchEvent: list) {
                // 받아서 작업
            }

            // 처리하고 나면, 이전 작업들은 없애줘야함
            boolean valid = watchKey.reset(); // 초기화에 성공하지 못했거나, 더이상 유효하지 않으면 false 리턴
            if(!valid) break;

        }

    }
}