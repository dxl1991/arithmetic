package nio_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Test {

    public static void main(String[] args) throws Exception{
//        readFile();
        Path dir = Paths.get("D:\\java");
        listDirectory(dir);
        Path startingDir = Paths.get("D:\\java");
        List<Path> result = new LinkedList<>();
        Files.walkFileTree(startingDir, new FindJavaVisitor(result));//会遍历目录下的所有目录
        System.out.println("result.size()=" + result.size());
    }

    public static void readFile() throws IOException {
        String s = "test.txt";
        File file = new File(s);
        if(!file.exists()){
            file.createNewFile();
        }
        Path path = file.toPath();
        System.out.println(path.toAbsolutePath()); //输出绝对路径
        File file1 = path.toFile();
        Path path1 = path.toAbsolutePath().getParent().resolve("test2.txt");//在路径后面追加一个路径
//        Files.copy(path, path1);//复制文件，包括内容，目标文件存在会报错
        System.out.println(path1.toAbsolutePath());
        System.out.println(file1.getName());
        BufferedWriter newWriter = Files.newBufferedWriter(path, UTF_8, CREATE, APPEND);
        newWriter.write("first line\n"); //写入缓存区
        newWriter.flush(); //写入文件
        newWriter.close();
        BufferedReader bufferedReader = Files.newBufferedReader(path);
        bufferedReader.lines().forEach(str -> System.out.println(str)); //按行遍历文件
        bufferedReader.close();

    }

    /**
     * 遍历文件夹
     * @param path
     */
    public static void listDirectory(Path path){
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
            for(Path e : stream){
                if(Files.isDirectory(e)){
                    listDirectory(e);
                }else{
                    System.out.println(e.getFileName());
                }
            }
        }catch(IOException e){

        }
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        public FindJavaVisitor(List<Path> result){
            this.result = result;
        }
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
            if(file.toString().endsWith(".jpg")){
                result.add(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
