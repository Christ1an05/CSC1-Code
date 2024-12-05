import java.util.ArrayList;

/*
 * Class for representing an individual member of a family tree
 *
 * A family member has a unique id, a name, and a list of children (who are themselves family members)
 */
public class FamilyMember {

    private String id;
    private String name;
    private ArrayList<FamilyMember> children;

    /*
     * Constructor for creating a new family member. The constructor takes a unique identifier
     * as input and uses this to initialize the id instance field. The name is set to be
     * equal to the id, except with all numeric identifiers stripped away. children is
     * initialized to be an empty list
     *
     * @param id - the unique identifier for the family member being created
     */
    public FamilyMember(String id) {
        this.id = id;
        int underscore = id.indexOf("_");
        if (underscore != -1) {
            this.name = id.substring(0, underscore);
        } else {
            this.name = id;
        }
        children = new ArrayList<FamilyMember>();
    }

    /*
     * Getter method for family member name
     *
     * @return name - the name of the family member
     */
    public String getName() {
        return this.name;
    }

    /*
     * Setter method for the name of the family member
     *
     * @param name - the new name of the family member
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Getter method for the list of children
     *
     * @return children - the list of family members who are children of this object
     */
    public ArrayList<FamilyMember> getChildren() {
        return this.children;
    }

    /*
     * Method for adding a new parent-child relationship to the tree.
     *
     * @param child - the new family member that is to be listed as a child of this member
     */
    public void addChild(FamilyMember child) {
        this.children.add(child);
    }

    /*
     * Creates a string representation of the object (simply returns the name)
     */
    public String toString() {
        return this.name;
    }

    /*
     * Tests if another object is equal to this family member
     *
     * @param other - the object against which to compare this family member
     *
     * @return true if other is a Family Member with the same id, and false otherwise
     */
    public boolean equals(Object other) {
        if (!(other instanceof FamilyMember)) {
            return false;
        }
        FamilyMember otherMember = (FamilyMember) other;
        return otherMember.id.equals(this.id);
    }

    /*
     * Instance method to test if this family member has a descendant with the name given as input
     *
     * @param name - the name that we are searching for among our descendants
     *
     * @return true if this member has a descendant whose name matches the parameter name
     */
    public boolean hasDescendant(String name) {
        // Check if the current member's name matches the input
        if (this.getName().equals(name)) {
            return true;
        }

        // Recursively check each child to see if any of them have the matching name
        for (FamilyMember child : this.getChildren()) {
            if (child.hasDescendant(name)) {
                return true;
            }
        }

        // If no match is found, return false
        return false;
    }

    /*
     * Instance method to compute the longest lineage originating from this FamilyMember
     * object (i.e. the person who called the method).
     *
     * @return longestLineage - an ArrayList<String> containing the names of all of the
     * people who form the longest lineage, in the order from oldest to youngest.
     */
    public ArrayList<String> longestLineage() {
        // Initialize the result list
        ArrayList<String> longestLineage = new ArrayList<>();

        // Call the helper method to populate the longest lineage
        longestLineageHelper(this, new ArrayList<>(), longestLineage);

        return longestLineage;
    }

    // Recursive helper method for longestLineage
    private void longestLineageHelper(FamilyMember member, ArrayList<String> currentLineage, ArrayList<String> longestLineage) {
        // Add the current member's name to the lineage
        currentLineage.add(member.getName());

        // Check if this is a leaf node (no children)
        if (member.getChildren().isEmpty()) {
            // Update the longest lineage if the current one is longer
            if (currentLineage.size() > longestLineage.size()) {
                longestLineage.clear();
                longestLineage.addAll(currentLineage);
            }
        } else {
            // Recur for each child
            for (FamilyMember child : member.getChildren()) {
                longestLineageHelper(child, new ArrayList<>(currentLineage), longestLineage);
            }
        }
    }
}
