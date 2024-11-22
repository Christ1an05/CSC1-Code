import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class FamilyTreeTest
{   
    /*
     * Method for creating out a hierarchical representation of a tree. This recursive helper
     * method generates a String for the subtree rooted at a particular node
     *
     * @param subroot - the family member whose personal lineage is to be generated
     * @param padding - the indentation/tree lines that preceed this node (based on its depth down the tree)
     * @param pointer - the symbol preceding the family member's name (based on location in the tree)
     * @param isLast - true if subroot is the last child of his/her/their parent
     */
    private static String subtreeString(FamilyMember subroot, String padding, String pointer, boolean isLast)
    {
        StringBuilder sb = new StringBuilder();
        // start the new node on its own line
        sb.append("\n");
        // padding ensures that the family member's name is printed at a proper depth
        sb.append(padding);
        // symbol denoting the "branch" leading to this family member
        sb.append(pointer);
        sb.append(subroot.getName());
        
        // Determine what padding string will precede all of this node's children
        // The child will have just as much padding as the parent, as well as one extra level
        String childPadding;
        if(isLast)
        {
            // if this is the last child, then the padding doesn't need an extra connecting branch
            childPadding = padding + "   ";
        }
        else
        {
            // if this is not the last child, then the padding needs a branch
           childPadding = padding + "│  ";
        }
        
        ArrayList<FamilyMember> children = subroot.getChildren();
        for(int i = 0; i < children.size(); i++)
        {
            FamilyMember child = children.get(i);
            boolean lastChild = i == children.size() - 1;
            
            // the very last child will have a t-shaped pointer; otherwise an l-shaped pointer
            String childPointer;
            if(!lastChild)
            {
                childPointer = "├──>";
            }
            else
            {
                childPointer = "└──>";
            }
            
            // recursively convert the child (and its subtree) into a String
            String childTree = subtreeString(child, childPadding, childPointer, lastChild);
            // the child's subtree gets added on to the bigger subtree
            sb.append(childTree);
        }
        return sb.toString();
    }
    
    /*
     * Main recursive method for printing out a String representation of one's family tree
     *
     * @param progenitor - the oldest member at the very top of the family tree
     */
    public static void printTree(FamilyMember progenitor)
    {
        if(progenitor == null)
        {
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        // progenitor starts at the far left, no padding
        String padding = "";
        // progenitor does not have a branch pointing to it
        String pointer = "";
        
        // generate a string representation of the tree starting from the progenitor
        // progenitor is "last", no other children at his/her/their level
        String treeString = subtreeString(progenitor, padding, pointer, true);
        System.out.println(treeString);
    }
    
    // helper method for printing out a chunk of spaces followed by a vertical line
    public static void printChunk(int chunkLength)
    {
        printChunkSep(chunkLength, ' ');
    }
    
    // helper method for printing out a chunk of the same character followed by a vertical line
    public static void printChunkSep(int chunkLength, char sep)
    {
        for(int i = 0; i < chunkLength - 1; i++)
        {
            System.out.print(sep);
        }
        System.out.print("|");
    }
    
    /*
     * Print out row separator. It prints out a number of dashes equal to the total length
     * of all of the columns - except it prints out a vertical line at the end of each column
     *
     * @param columnLengths - list of the lengths of each of the individual columns. The individual
     * lengths are needed (rather than the total length) so that a vertical line can be added
     * at the end of each column
     */
    public static void printRowDashes(ArrayList<Integer> columnLengths)
    {
        for(int columnLength : columnLengths)
        {
            printChunkSep(columnLength, '-');
        }
        System.out.print("\n");
    }
    
    /*
     * Static method for testing the hasDescendant method. Creates a table and fills in the
     * table to using the method.
     *
     * @param ancestors - the list of ancestors of whom we are looking for descendants
     * @param descendantNames - the list of names that we are searching for in the family tree
     */     
    public static void testDescendantMethod(ArrayList<FamilyMember> ancestors, ArrayList<String> descendantNames)
    {
        // Need to compute the maximum name length to know how long to make the first column
        int maxAncestorNameLength = 0;
        // Need to compute individual column lengths in order to use the printRowDashes method
        for(FamilyMember ancestor : ancestors)
        {
            int ancestorNameLength = ancestor.getName().length();
            int columnLength = ancestorNameLength + 2 + 2; // padding of 2 spaces on each side
            maxAncestorNameLength = Math.max(ancestorNameLength, maxAncestorNameLength);
        }
        int firstColLength = maxAncestorNameLength + 2; // buffer of 2 spaces after column label
        
        ArrayList<Integer> columnLengths = new ArrayList<Integer>();
        // loop over family members to compute longest name and set of all name lengths
        columnLengths.add(firstColLength);
        
        for(String descendantName : descendantNames)
        {
            int descendantNameLength = descendantName.length();
            int columnLength = descendantNameLength + 2 + 2; // padding of 2 spaces on each side
            columnLengths.add(columnLength);
        }
        
        // Row 1
        // column 1 contains just row labels, so just blank spaces in row 1 column 1
        printChunk(firstColLength);
        
        // print out each column labels from row 1
        for(String descendantName : descendantNames)
        {
            System.out.print("  " + descendantName + " |");
        }
        System.out.print("\n");
        
        // row separator between row 1 and row 2
        printRowDashes(columnLengths);
        
        // rest of the table rows
        
        // Make a row for each family member
        for(FamilyMember ancestor : ancestors)
        {
            // print out the family member's name to label the row
            int padding = maxAncestorNameLength - ancestor.getName().length() + 2;
            System.out.print(ancestor.getName());
            printChunk(padding);
            
            // Go through all of the columns corresponding to the other family members
            for(String descendantName : descendantNames)
            {
                // Within each table cell, put a checkmark or blank space halfway inside the table cell
                
                // blank spaces on the first half of the table cell
                System.out.print("  ");
                int firstHalfLength = descendantName.length() / 2;
                for(int i = 0; i < firstHalfLength; i++)
                {
                    System.out.print(" ");
                }
                
                // if check mark if this table cell represents an ancestor relationship
                if(ancestor.hasDescendant(descendantName))
                {
                    System.out.print("x");
                }
                else
                {
                    System.out.print(" ");
                }
                
                // blank spaces on the second half of the table cell.
                int secondHalfLength = descendantName.length() - firstHalfLength - 1;
                printChunk(secondHalfLength + 2);
            }
            System.out.print("\n");
            
            // separator between the row corresponding to this family member and the row corresponding to the next member
            printRowDashes(columnLengths);
        }
    }
    
    /*
     * Static method to test the longestLineage method. It will compute the longest lineage
     * starting from the person given as input, and print out the lineage nicely.
     *
     * @param progenitor - the FamilyMember from whom the longest lineage should originate
     */
    public static void testLineageMethod(FamilyMember progenitor)
    {
        ArrayList<String> longestLineage = progenitor.longestLineage();
        String padding = "";
        for(String name : longestLineage)
        {
            System.out.print(padding);
            if(padding.length() > 0)
            {
                System.out.print("└──>");
            }
            System.out.println(name);
            padding += " ";
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        // Read in the tree file specified as the first command line argument
        String fileName = args[0];
        FileReader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);
        
        // skip over the first line - we don't need it
        String header = scanner.nextLine();
        
        // Determine which names will be used for testing the ancestorOf method
        // pick the names based on which test dataset was used
        String[] ancestorNamesArray = null;
        String[] descendantNamesArray = null;
        if(fileName.endsWith("old-testament-family-tree.csv"))
        {
            ancestorNamesArray = new String[]{"Adam", "Cain", "Abel", "Seth", "Noah", "Abraham", "Isaac", "Ishmael", "Moses", "Solomon"};
            descendantNamesArray = new String[]{"Michael", "Miriam", "Eliezer", "Amon", "Jabal", "Mahalath", "Put", "Midian", "Noah", "Jesus"};
        }
        else if(fileName.endsWith("cyrus-trask-family-tree.csv"))
        {
            ancestorNamesArray = new String[]{"Cyrus Trask", "Charles Trask", "Adam Trask", "Aaron Trask", "Caleb Trask"};
            descendantNamesArray = new String[]{"Cyrus Trask", "Charles Trask", "Adam Trask", "Aaron Trask", "Caleb Trask", "Abra"};
        }
        else if(fileName.endsWith("jong-family-tree.csv"))
        {
            ancestorNamesArray = new String[]{"Lindo's Mom", "Lindo Jong", "Waverly Jong", "Winston Jong", "Vincent Jong", "Shoshana Chen"};
            descendantNamesArray = new String[]{"Lindo's Mom", "Lindo Jong", "Waverly Jong", "Winston Jong", "Vincent Jong", "Shoshana Chen"};
        }
        else if(fileName.endsWith("sample-family-tree.csv"))
        {
            ancestorNamesArray = new String[]{"Charlie", "Snoopy", "Schroeder", "Patty", "Marcy"};
            descendantNamesArray = new String[]{"Charlie", "Woodstock", "Marbles", "Spike", "Belle", "Otto"};
        }
        ArrayList<String> ancestorNames = new ArrayList<String>(Arrays.asList(ancestorNamesArray));
        ArrayList<FamilyMember> ancestors = new ArrayList<FamilyMember>();
        
        ArrayList<String> descendantNames = new ArrayList<String>(Arrays.asList(descendantNamesArray));
        
        // hashtable that will allow us to find the FamilyMember object associated with a given name
        HashMap<String, FamilyMember> nodes =  new HashMap<String, FamilyMember>();
        
        FamilyMember progenitor = null;
        
        while(scanner.hasNextLine())
        {
            // every line should be in the format "parent, child"
            String line = scanner.nextLine();
            String[] names = line.split(", ");
            String parentID = names[0];
            String childID = names[1];
            
            if(parentID.equals(childID))
            {
                continue;
            }
            
            // if we have not already created a FamilyMember object corresponding to the parent, we do so
            if(!nodes.containsKey(parentID))
            {
                FamilyMember parent = new FamilyMember(parentID);
                String parentName = parent.getName();
                if(ancestorNames.contains(parentName))
                {
                    ancestors.add(parent);
                    ancestorNames.remove(parentName); // avoid future duplicates in the table
                }
                nodes.put(parentID, parent);
            }
            
            // if we have not already created a FamilyMember object corresponding to the child, we do so
            if(!nodes.containsKey(childID))
            {
                FamilyMember child = new FamilyMember(childID);
                String childName = child.getName();
                if(ancestorNames.contains(childName))
                {
                    ancestors.add(child);
                    ancestorNames.remove(childName); // avoid future duplicates in the table
                }
                nodes.put(childID, child);
            }
            
            // Add the parent-child relationship to the tree
            
            FamilyMember parent = nodes.get(parentID);
            
            // we assume that the first member of the dataset is the progenitor
            if(progenitor == null)
            {
                progenitor = parent;
            }
            
            FamilyMember child = nodes.get(childID);
            
            parent.addChild(child);
        }
        
        printTree(progenitor);
        
        System.out.println();
        
        testDescendantMethod(ancestors, descendantNames);
        
        System.out.println("\nLongest Lineage:");
        System.out.println("----------------");
        testLineageMethod(progenitor);
    }
}
