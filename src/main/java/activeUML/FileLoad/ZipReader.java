package activeUML.FileLoad;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * ZipReader reads the zip file specified using ZipInputStream,
 * it then outputs the raw files into the output directory
 * specified as a parameter.
 */
public class ZipReader
{
    private static final Logger LOGGER = Logger.getLogger("ZipReader");
    // Expands the zip file passed as argument 1, into the
    // directory provided in argument 2
    public static void main(String args[]) throws Exception
    {


        // create a buffer to improve copy performance later.
        byte[] buffer = new byte[2048];

        Path outDir = Paths.get("/Users/jeremybutler/Desktop/ActiveUML/target/outs/");

        try(
                // we open the zip file using a java 7 try with resources block so
                // that we don't need a finally.
                ZipInputStream stream = new ZipInputStream(new FileInputStream("/Users/jeremybutler/Desktop/ActiveUML/target/ActiveUML-0.0.1-SNAPSHOT.jar"))
        )
        {
            LOGGER.info("Zip file:  has been opened");

            // now iterate through each file in the zip archive. The get
            // next entry call will return a ZipEntry for each file in
            // the stream
            ZipEntry entry;
            while((entry = stream.getNextEntry())!=null)
            {




                // Now we can read the file data from the stream. We now
                // treat the stream like a usual input stream reading from
                // it until it returns 0 or less.

                if(entry.getName().endsWith("class")){
                    FileOutputStream  output = null;
                    try
                    {
                        int i = entry.getName().lastIndexOf("/");

                        Path folderPath = outDir.resolve(entry.getName().substring(0,i));

                        Path filePath = outDir.resolve(entry.getName());
                        folderPath.toFile().mkdirs();

                        File f = filePath.toFile();


                        f.createNewFile();
                        output = new FileOutputStream(f);

                        LOGGER.info("Writing file: " + filePath);
                        int len;
                        while ((len = stream.read(buffer)) > 0)
                        {
                            output.write(buffer, 0, len);
                        }
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                    finally {
                        if(output != null){
                            output.close();
                        }
                    }
                }

            }
        }
    }
}