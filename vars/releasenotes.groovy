import java.io.*;
import groovy.io.*;
import java.util.Calendar.*;
import java.text.SimpleDateFormat;

@NonCPS
def call(Map config=[:]) {
    // "C:\\repository\\private\\jenkinslab\\jenkinstest\\ConsoleApp1"
    def dir = new File(pwd());
    println("FilePath: ${dir.path}/releasenotes.txt");
    new File( "${dir.path}/releasenotes.txt" ).withWriter('utf-8'){
    	writer ->
    	dir.eachFileRecurse { file ->
    		if (file.isDirectory()) {
    		    writer.writeLine("${file.name}")
    		} else {
    		    writer.writeLine("\t${file.name}\t${file.length()}")
    		}
    	}
    }
    def date = new Date();
    def sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
    echo "Data and time is: ${sdf.format(date)}"
    if (config.changes != "false") {
        echo "Found changes";
    }
}