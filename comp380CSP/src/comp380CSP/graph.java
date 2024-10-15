package comp380CSP;

public class graph {
    boolean adjMatrix[][]; //define adjMatrix to hold provinces/territories
    int numNodes; //define number of provinces
    String[] provinceNames;
    String[] colors;

    //initialize the matrix with constructors
    public graph(int numNodes) {
        this.numNodes = numNodes;
        adjMatrix = new boolean[numNodes][numNodes];
        provinceNames = new String[numNodes]; //initialize provinceNames array
    }

    //function to add borders between provinces
    public void addBorder(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    //getters and setters for province name for a node
    public void setProvinceName(int nodeIndex, String provinceName) {
        provinceNames[nodeIndex] = provinceName;
    }

    public String getProvinceName(int nodeIndex) {
        return provinceNames[nodeIndex];
    }

    //function provides basis for backtracking algorithm to happen
    public void colorProvinces(String[] availableColors) {
        colors = availableColors;
        String[] colorAssignments = new String[numNodes];
        if (colorRecursive(0, colorAssignments)) {//call the recursive backtrack algorithm with the first province and the colors
            
            for (int i = 0; i < numNodes; i++) {//print the color assignments
                System.out.println(provinceNames[i] + ": " + colorAssignments[i]);
            }
        } else {
            System.out.println("Unable to color the provinces");
        }
    }

    //method for recursive backtracking
    private boolean colorRecursive(int provinceIndex, String[] colorAssignments) {
        if (provinceIndex == numNodes) {//when the index reaches the amount of provinces, this is exit case as solutions are found
            return true;
        }
        String province = provinceNames[provinceIndex];//get the initial province at current index and for each of the colors, make sure that color is safe
        for (String color : colors) {
            if (isSafeColor(province, color, provinceIndex, colorAssignments)) {
                colorAssignments[provinceIndex] = color; //if color is safe, assign it to the province
                if (colorRecursive(provinceIndex + 1, colorAssignments)) {//recursively call itself with the next indexed province
                    return true;
                }
                colorAssignments[provinceIndex] = null; //if a province is unable to get a color that is safe, assign it this string
            }
        }
        return false;//if none of the colors can be used, return false and graph is unsolvable
    }

    //check if assigning a color to a province is safe
    private boolean isSafeColor(String province, String color, int provinceIndex, String[] colorAssignments) {
        for (int i = 0; i < numNodes; i++) {//iterate through provinces and check if the adjacent province has the same color
        	//first case checks to make sure there is an adjacent province, then if it has been assigned a color, then if it is the same color as the adjacent matrix
            if (adjMatrix[provinceIndex][i] && colorAssignments[i] != null && colorAssignments[i].equals(color)) {
                return false; //adjacent province has the same color, so return false
            }
        }
        return true; //otherwise it is assigned a safe color
    }

    // Data setup for initialization
    public static void setData(graph graph) {
        // Add provinces/territories names
        String[] provinceNames = { "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
                "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec",
                "Saskatchewan", "Yukon" };

        // Set province names in the graph
        for (int i = 0; i < provinceNames.length; i++) {
            graph.setProvinceName(i, provinceNames[i]);
        }

        // Define adjacency relations between provinces/territories

        graph.addBorder(0, 1); // Alberta, BC
        graph.addBorder(0, 11); // Alberta, Saskatchewan
        graph.addBorder(0, 5); // Alberta, Northwest Territories

        graph.addBorder(1, 5); // BC, Northwest Territories
        graph.addBorder(1, 12);// BC, Yukon

        graph.addBorder(11, 2);// Saskatchewan, Manitoba
        graph.addBorder(11, 5);// Saskatchewan, Northwest Territories

        graph.addBorder(2, 7); // Manitoba, Nunavut
        graph.addBorder(2, 8); // Manitoba, Ontario

        graph.addBorder(8, 10); // Ontario, Quebec

        graph.addBorder(4, 10); // Newfoundland, Quebec

        graph.addBorder(8, 3); // Quebec, NB

        graph.addBorder(3, 6); // NB, NS

        graph.addBorder(6, 9); // NS, PEI

    }

    public static void main(String args[]) {
        // Create a graph object for Canadian provinces/territories
        int numProvinces = 13;
        graph canadaGraph = new graph(numProvinces);

        // Setup data for Canadian provinces/territories
        setData(canadaGraph);

        String[] availableColors = { "Red", "Green", "Blue" };
        canadaGraph.colorProvinces(availableColors);

    }
}
