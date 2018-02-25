package com.mycompany.huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Huffman kode som laster inn en valg fil eller lar deg skrive en tekst.
 * Printer ut char intervallene i teksten. Printer ogs? ut den bin?ere koden for bokstavene basert p? oppbygningen av huffmantreet
 * Etter et huffmantre er laget er det mulig ? skrive inn bin?re tall for ? f? ut en tekst basert p? strukturen av huffman treet
 * Kan kun bruke bokstaver som var i den opprinelige teksten.
 *
 * Tekstfilen som brukes har f?lgende adresse C:\temp\huffmanTest.txt
 * dette m? forandres i koden hvis du ?nsker ? bruke en annen fil. ...
 * */

public class Huffman {
    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;

    static PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    static TreeMap<Character, String> codes = new TreeMap<>();
    static String text = "";
    static String encoded = "";
    static String decoded = "";
    static int ASCII[] = new int[255];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = (readFromFile) ? new Scanner(new File("input.txt")) : new Scanner(System.in);
        int decision = 0;
        while (decision != -1) {
            if (handlingDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);

        }
    }

    /*
    Meny for valg mellom ? skrive inn egen tekst eller hente inn en lokal txt fil.
     */
    private static int consoleMenu(Scanner scanner) {
        int decision;
        System.out.println("\n---- Menu ----\n" +
                "-- [-1] to exit \n" +
                "-- [1] to enter text\n" +
                "-- [2] to load text file\n" +
                "-- [3] to decode binary text");
        decision = Integer.parseInt(scanner.nextLine());
        if (readFromFile)
            System.out.println("Decision: " + decision + "\n---- End of Menu ----\n");
        return decision;
    }

    /*
    Leser hva du velger i menyen.
    1 Scanner og kj?rer metode for ? h?ntere tekst
    2 kj?rer metode for ? laste inn txt fil
     */
    private static boolean handlingDecision(Scanner scanner, int decision) throws FileNotFoundException {
        if (decision == 1) {
            if (handleNewText(scanner)) return true;
        } else if (decision == 2) {
            importTextFile();
            return false;
        } else if (decision == 3) {
            handleDecodingNewText(scanner);
        } else if (decision != 1 || decision !=2 || decision != 3){
            System.out.println("Invalid command");
        }
        
        return false;
    }

    /*
    Scanner teksten fra en fil og kj?rere metoer for ? konvertere dette til et huffman tree
     */
    private static void importTextFile() throws FileNotFoundException {
        try {
            String charset = "UTF-8";
            Path path = Paths.get("C:\\Users\\vegar\\OneDrive\\Documents\\Utdanning\\IS-211 Algoritmer og datastrukturer\\Oblig2\\Mandatory2\\terjevigen.txt");
            Scanner sc = new Scanner(path, charset);
            StringJoiner sj = new StringJoiner(" ");
            while (sc.hasNext()) {
                sj.add(sc.next());
            }
            
            text = sj.toString();
            ASCII = new int[255];
            nodes.clear();
            codes.clear();
            encoded = "";
            decoded = "";
            System.out.println("Text: " + text);
            calculateCharIntervals(nodes, true);
            buildTree(nodes);
            generateCodes(nodes.peek(), "");
            
            printCodes();
            System.out.println("-- Encoding/Decoding --");
            encodeText();
            decodeText();
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /*
    Metode for ? scanne bin?r tekst
     */
        private static void handleDecodingNewText(Scanner scanner) {
            System.out.println("Enter the text to decode:");
            encoded = scanner.nextLine();
            System.out.println("Text to Decode: " + encoded);
            decodeText();
        }

    /*
    Scanner inn teksten og kj?rer metoder for ? bygge et huffman tree basert p? teksten.
     */
    private static boolean handleNewText(Scanner scanner) {
        int oldTextLength = text.length();
        System.out.println("Enter the text:");
        text = scanner.nextLine();
        if (newTextBasedOnOldOne && (oldTextLength != 0 && !IsSameCharacterSet())) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        ASCII = new int[255];
        nodes.clear();
        codes.clear();
        encoded = "";
        decoded = "";
        System.out.println("Text: " + text);
        calculateCharIntervals(nodes, true);
        buildTree(nodes);
        generateCodes(nodes.peek(), "");

        printCodes();
        System.out.println("-- Encoding/Decoding --");
        encodeText();
        decodeText();
        return false;


    }

    private static boolean IsSameCharacterSet() {
        boolean flag = true;
        for (int i = 0; i < text.length(); i++)
            if (ASCII[text.charAt(i)] == 0) {
                flag = false;
                break;
            }
        return flag;
    }

    /*
    Kj?rer den bin?re tallrekef?lgen som vi f?r fra encodeText() metoden
    og gj?r den om til bokstaver basert p? huffman treet.
    Dette er for ? sjekke at den greier ? dekode sekvensen vi f?r generert av ? lage huffman treet.
    Brukes og til ? skrive inn bin?re tall for ? f? ut tekst basert p? tallkoden av hver bokstav som genereres
    ved ? lage et huffman tree.
    Kan kun bruke bokstaver som var i den opprinelige teksten.
    */
    private static void decodeText() {
        decoded = "";
        Node node = nodes.peek();
        for (int i = 0; i < encoded.length(); ) {
            Node tmpNode = node;
            while (tmpNode.left != null && tmpNode.right != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1')
                    tmpNode = tmpNode.right;
                else tmpNode = tmpNode.left;
                i++;
            }
            if (tmpNode != null)
                if (tmpNode.character.length() == 1)
                    decoded += tmpNode.character;

        }
        System.out.println("Decoded Text: " + decoded);
    }

    /*
    Viser teksten i bin?re tall.
     */
    private static void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++)
            encoded += codes.get(text.charAt(i));
        System.out.println("Encoded Text: " + encoded);
    }

    /*
    bygger et huffman tre basert p? klassen PriorityQueue frabibloteket og klassen node(i bunnen av filen)
     */
    private static void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    /*
    Printer ut tegnene og bin?re verdien de har i huffman treet
     */
    private static void printCodes() {
        System.out.println("--- Printing Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    /*
    G?r gjennom teksten og finner hvor mange ganger tegnene er brukt. lager s? noder basert p?? antal intervller av
    tegnet i teksten.
     */
    private static void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
        if (printIntervals) System.out.println("-- intervals --");

        for (int i = 0; i < text.length(); i++)
            ASCII[text.charAt(i)]++;

        for (int i = 0; i < ASCII.length; i++)
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                if (printIntervals)
                    System.out.println("'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
            }
    }

    /*
    Generer plasseringen for nodene
     */
    private static void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null)
                generateCodes(node.right, s + "1");

            if (node.left != null)
                generateCodes(node.left, s + "0");

            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}

/*
Klassen node. Denne blir brukt for ? opprette en node og gi den plasering p? huffman treet.
alts? h?yre/venste for root og verdien nedover i treet.
 */
class Node {
    Node left, right;
    double value;
    String character;

    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}