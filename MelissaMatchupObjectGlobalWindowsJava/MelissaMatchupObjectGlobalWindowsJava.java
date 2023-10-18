import com.melissadata.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MelissaMatchupObjectGlobalWindowsJava {

  public static void main(String args[]) throws IOException {
    // Variables
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testGlobalFile = arguments[1];
    String testUsFile = arguments[2];
    String dataPath = arguments[3];

    RunAsConsole(license, testGlobalFile, testUsFile, dataPath);
  }

  public static String[] ParseArguments(String[] args) {
    String license = "", testGlobalFile = "", testUsFile = "", dataPath = "";
    List<String> argumentStrings = Arrays.asList("--license", "-l", "--global", "-g", "--us", "-u", "--dataPath", "-d");
    for (int i = 0; i < args.length - 1; i++) {
      if ((args[i].equals("--license") || args[i].equals("-l")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          license = args[i + 1];
        }
      }
      if ((args[i].equals("--global") || args[i].equals("-g")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testGlobalFile = args[i + 1];
        }
      }
      if ((args[i].equals("--us") || args[i].equals("-u")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testUsFile = args[i + 1];
        }
      }
      if ((args[i].equals("--dataPath") || args[i].equals("-d")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          dataPath = args[i + 1];
        }
      }
    }
    return new String[] { license, testGlobalFile, testUsFile, dataPath };

  }

  public static void RunAsConsole(String license, String testGlobalFile, String testUsFile, String dataPath) throws IOException {
    System.out.println("\n\n================= WELCOME TO MELISSA MATCHUP OBJECT GLOBAL WINDOWS JAVA =================\n");
    MatchupObjectGlobal matchUpObjectGlobal = new MatchupObjectGlobal(license, dataPath);

    boolean shouldContinueRunning = true;

    if (!matchUpObjectGlobal.mdMatchUpObjGlobal.GetInitializeErrorString().equals("No Error")) {
      shouldContinueRunning = false;
    }

    while (shouldContinueRunning) {
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      if (testGlobalFile.isEmpty() && testUsFile.isEmpty()) {
        System.out.println("\nFill in each value to see the MatchUp Object Global results");

        System.out.print("Global Input File: ");
        dataContainer.InputFilePath1 = stdin.readLine();

        System.out.print("US Input File: ");
        dataContainer.InputFilePath2 = stdin.readLine();
      } else {
        dataContainer.InputFilePath1 = testGlobalFile;
        dataContainer.InputFilePath2 = testUsFile;
      }

      dataContainer.OutputFilePath1 = (dataContainer.FormatOutputFile(dataContainer.InputFilePath1));
      dataContainer.OutputFilePath2 = (dataContainer.FormatOutputFile(dataContainer.InputFilePath2));

      // Print user input
      System.out.println("\n========================================= INPUTS ========================================\n");

      List<String> sections = dataContainer.GetWrapped(dataContainer.InputFilePath1, 50);

      System.out.println("\t         Global Input File: " + sections.get(0));

      for (int i = 1; i < sections.size(); i++) {
        if ((i == sections.size() - 1) && sections.get(i).endsWith("/")) {
          sections.set(i, sections.get(i).substring(0, sections.get(i).length() - 1));
        }
        System.out.println("\t                            " + sections.get(i));
      }

      sections = dataContainer.GetWrapped(dataContainer.InputFilePath2, 50);

      System.out.println("\t             US Input File: " + sections.get(0));

      for (int i = 1; i < sections.size(); i++) {
        if ((i == sections.size() - 1) && sections.get(i).endsWith("/")) {
          sections.set(i, sections.get(i).substring(0, sections.get(i).length() - 1));
        }
        System.out.println("\t                            " + sections.get(i));
      }

      // Execute MatchUp Object Global
      matchUpObjectGlobal.ExecuteObjectAndResultCodes(dataContainer.InputFilePath1, dataContainer.OutputFilePath1);
      matchUpObjectGlobal.ExecuteObjectAndResultCodes(dataContainer.InputFilePath2, dataContainer.OutputFilePath2);

      // Print output
      System.out.println("\n========================================= OUTPUT ========================================\n");

      sections = dataContainer.GetWrapped(dataContainer.FormatOutputFile(dataContainer.InputFilePath1), 50);

      System.out.println("\n  MatchUp Object Global Information:");

      System.out.println("\t        Global Output File: " + sections.get(0));

      for (int i = 1; i < sections.size(); i++) {
        if ((i == sections.size() - 1) && sections.get(i).endsWith("/")) {
          sections.set(i, sections.get(i).substring(0, sections.get(i).length() - 1));
        }
        System.out.println("\t                            " + sections.get(i));
      }

      sections = dataContainer.GetWrapped(dataContainer.FormatOutputFile(dataContainer.InputFilePath2), 50);

      System.out.println("\t            US Output File: " + sections.get(0));

      for (int i = 1; i < sections.size(); i++) {
        if ((i == sections.size() - 1) && sections.get(i).endsWith("/")) {
          sections.set(i, sections.get(i).substring(0, sections.get(i).length() - 1));
        }
        System.out.println("\t                            " + sections.get(i));
      }

      boolean isValid = false;
      if (!(testGlobalFile.isEmpty() && testUsFile.isEmpty())) {
        isValid = true;
        shouldContinueRunning = false;
      }
      while (!isValid) {
        System.out.println("\nTest another file? (Y/N)");
        String testAnotherResponse = stdin.readLine();

        if (!testAnotherResponse.isEmpty()) {
          testAnotherResponse = testAnotherResponse.toLowerCase();
          if (testAnotherResponse.equals("y")) {
            isValid = true;
          } else if (testAnotherResponse.equals("n")) {
            isValid = true;
            shouldContinueRunning = false;
          } else {
            System.out.print("Invalid Response, please respond 'Y' or 'N'");
          }
        }
      }
    }
    System.out.println("\n========================= THANK YOU FOR USING MELISSA JAVA OBJECT =======================\n");
  }
}

class MatchupObjectGlobal {
  // Path to Name Object data files (.dat, etc)
  String dataFilePath;

  // Create instance of Melissa Name Object
  mdMUReadWrite mdMatchUpObjGlobal = new mdMUReadWrite();

  public MatchupObjectGlobal(String license, String dataPath) {
    // Set license string and set path to data files (.dat, etc)
    mdMatchUpObjGlobal.SetLicenseString(license);
    dataFilePath = dataPath;
    mdMatchUpObjGlobal.SetPathToMatchUpFiles(dataFilePath);
    mdMatchUpObjGlobal.SetKeyFile("temp.key");
    mdMatchUpObjGlobal.SetMatchcodeName("Global Address");
    mdMatchUpObjGlobal.SetMaximumCharacterSize(1);

    // If you see a different date than expected, check your license string and
    // either download the new data files or use the Melissa Updater program to
    // update your data files.
    mdMUReadWrite.ProgramStatus pStatus = mdMatchUpObjGlobal.InitializeDataFiles();

    if (pStatus != mdMUReadWrite.ProgramStatus.ErrorNone) {
      // Problem during initialization
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }
    System.out.println("                   DataBase Date: " + mdMatchUpObjGlobal.GetDatabaseDate());
    System.out.println("                 Expiration Date: " + mdMatchUpObjGlobal.GetLicenseExpirationDate());

    /**
     * This number should match with the file properties of the Melissa Object
     * binary file.
     * If TEST appears with the build number, there may be a license key issue.
     */
    System.out.println("                  Object Version: " + mdMatchUpObjGlobal.GetBuildNumber());
    System.out.println();

  }

  // This will call the lookup function to process the input name as well as
  // generate the result codes
  public void ExecuteObjectAndResultCodes(String inputFilePath, String outputFilePath) {
    FileInputStream inFile = null;
    InputStreamReader fileReader = null;
    BufferedWriter outFile = null;

    String record;
    String[] fields;

    long total = 0, dupes = 0;

    // Establish field mappings: when you change the matchcode, you will change
    // these
    mdMatchUpObjGlobal.ClearMappings();

    if (mdMatchUpObjGlobal.AddMapping(mdMUReadWrite.MatchcodeMapping.Country) == 0 ||
        mdMatchUpObjGlobal.AddMapping(mdMUReadWrite.MatchcodeMapping.Address) == 0 ||
        mdMatchUpObjGlobal.AddMapping(mdMUReadWrite.MatchcodeMapping.Address) == 0 ||
        mdMatchUpObjGlobal.AddMapping(mdMUReadWrite.MatchcodeMapping.Address) == 0 ||
        mdMatchUpObjGlobal.AddMapping(mdMUReadWrite.MatchcodeMapping.Address) == 0) {
      System.out.println("\nError: Incorrect AddMapping() parameter");
      System.exit(1);
    }

    // Process the sample data file
    try {
      inFile = new FileInputStream(inputFilePath);
      fileReader = new InputStreamReader(inFile, StandardCharsets.UTF_8);

      outFile = new BufferedWriter(new FileWriter(outputFilePath, StandardCharsets.UTF_8, false));

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      record = bufferedReader.readLine(); // Read and discard the header line
      while ((record = bufferedReader.readLine()) != null) {
        // Read and parse pipe delimited record
        fields = record.split(Pattern.quote("|"));

        // Load up the fields
        mdMatchUpObjGlobal.ClearFields();

        mdMatchUpObjGlobal.AddField(fields[7]);
        mdMatchUpObjGlobal.AddField(fields[3]);
        mdMatchUpObjGlobal.AddField(fields[4]);
        mdMatchUpObjGlobal.AddField(fields[5]);
        mdMatchUpObjGlobal.AddField(fields[6]);

        // Create a UserInfo string which uniquely identifies the records
        mdMatchUpObjGlobal.SetUserInfo(fields[0]);

        // Build the key and submit it
        mdMatchUpObjGlobal.BuildKey();
        mdMatchUpObjGlobal.WriteRecord();
      }

      mdMatchUpObjGlobal.Process();

      String[] arr = new String[4];

      outFile.write("Id|ResultCodes|DupeGroup|Key");
      outFile.newLine();

      while (mdMatchUpObjGlobal.ReadRecord() != 0) {
        if (mdMatchUpObjGlobal.GetResults().contains("MS03")) {
          dupes++;
        }

        mdMatchUpObjGlobal.ClearFields();

        arr[0] = mdMatchUpObjGlobal.GetUserInfo();
        arr[1] = mdMatchUpObjGlobal.GetResults();
        arr[2] = String.valueOf(mdMatchUpObjGlobal.GetDupeGroup());
        arr[3] = mdMatchUpObjGlobal.GetKey().replaceAll("\\s+", " ");

        outFile.write(String.join("|", arr));
        outFile.newLine();

        total++;
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      try {
        if (inFile != null) {
          inFile.close();
        }
        if (fileReader != null) {
          fileReader.close();
        }
        if (outFile != null) {
          outFile.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // ResultsCodes explain any issues MatchUp Object Global has with the object.
    // List of result codes for MatchUp Object Global
    // https://wiki.melissadata.com/index.php?title=Result_Code_Details#MatchUp_Object
  }
}

class DataContainer {
  public String InputFilePath1 = "";
  public String InputFilePath2 = "";
  public String OutputFilePath1 = "";
  public String OutputFilePath2 = "";
  public String ResultCodes = "";

  public String FormatOutputFile(String inputFilePath) {
    File file = new File(inputFilePath);
    String filePath = file.getAbsolutePath();
    if (!file.exists()) {
      System.out.println("\nError: The input file does not exist");
      System.out.println(filePath + "\n");
      System.exit(1);
    }
    int location = inputFilePath.lastIndexOf(".");
    String outputFilePath = inputFilePath.substring(0, location) + "_output.txt";
    return outputFilePath;
  }

  public List<String> GetWrapped(String path, int maxLineLength) {
    File file = new File(path);
    String filePath = file.getAbsolutePath();
    String[] lines = filePath.split("\\\\");
    String currentLine = "";
    List<String> wrappedString = new ArrayList<>();
    for (String section : lines) {
      if ((currentLine + section).length() > maxLineLength) {
        wrappedString.add(currentLine.trim());
        currentLine = "";
      }
      if (section.contains(path)) {
        currentLine += section;
      } else {
        currentLine += section + "\\";
      }
    }
    if (currentLine.length() > 0) {
      wrappedString.add(currentLine.trim());
    }
    return wrappedString;
  }

}
