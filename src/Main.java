import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static class Settings {
        
        File output;
        boolean verbose = false;
        
    }
    
    public static void main(String[] args) throws IOException {

        Settings s = readCLA(args);

        ArrayList<String> hi = InputReader.readInput();

        FileWriter writer = null;
        if(s.output != null) {
            writer = new FileWriter(s.output);
        }

        for (String number : hi) {

            if(s.output != null) {
                writer.write(fixNumber(number) + '\n');
            } else {
                System.out.println(fixNumber(number));
            }
            
        }
        
        if(s.output != null) {
            writer.close();
        }

    }
    
    public static Settings readCLA(String[] args) {
        ArrayList<Character> tokens = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        
        for(String arg : args) {
            if(arg.charAt(0) == '-') {
                
                if(arg.length() == 1) {
                    tokens.add('\n');
                }
                
                if(arg.length() == 2) {
                    tokens.add(arg.charAt(1));
                }
                
                if(arg.length() > 2) {
                    for(char c : arg.substring(1).toCharArray()) {
                        tokens.add(c);
                    }
                }
                
            } else {
                
                values.add(arg);
                
            }
            
        }
        
        Settings s = new Settings();
        
        for(char c : tokens) {
            switch (c) {
                case 'v':
                    System.out.println("Verbosity activated.");
                    s.verbose = true;
                    break;
                case 'h':
                    System.out.println("Enter all the phone numbers you want fixed, followed by the word \"done\".");
                    break;
                case 'o':
                    if(values.size() == 0) {
                        System.out.println("No file path specified, ignoring.");
                    } else {
                        try {
                            File output = new File(values.get(0));
                            boolean fileCreated = output.createNewFile();
                            if(s.verbose) {
                                if(fileCreated) {
                                    System.out.println("Output file created as " + output);
                                } else {
                                    System.out.println(output + " already exists. Overwriting");
                                }
                            }
                            s.output = output;
                            values.remove(0);
                        } catch (IOException e) {
                            System.out.println("Error occured when creating file, ignoring.");
                        }

                    }
                    break;
                default:
                    System.out.println(c + " is not a valid command-line argument. Ignoring it.");
            }
        }
        return s;
        
    }
    
    public static String fixNumber(String number) {
        if(!number.equals("")) {
            if(number.charAt(0) == '7') {
                return ("+46" + number);
            } else if(number.charAt(0) == '4') {
                return ("+" + number);
            } else if(number.charAt(0) == '0') {
                return ("+46" + number.substring(1));
                
            } else {
                return number;
            }
        } else {
            return "";
        }
    }

}
