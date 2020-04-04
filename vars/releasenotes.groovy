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
    echo "Date time: ${sdf.format(date)} Build Number: ${BUILD_NUMBER}"
    if (config.changes != "false") {
        echo "Found changes";
    }
    
    def changeLogSets = currentBuild.changeSets;
    for(change in changeLogSets) {
        def entries = change.items;
        for(entry in entries) {
            echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
            for (file in entry.effectedFiles) {
                echo " ${file.editType.name} ${file.path}"
            }
        }
    }
    
    
    
    
    
    
}