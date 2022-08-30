// University of Balamand
// CSIS215 Object Oriented Programming Spring 19-20 Project 1
// Magic Square
//
//
// Author : Giorgio Murad
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        String s;   // String to take user input
        int option; // Integer representing the option by the user


        option = 0;
        do {
            s = JOptionPane.showInputDialog(
                    "Welcome to the Magic Square program." + "\n" +
                            "You can choose from the following three options:" + "\n" +
                            "1. Generate magic square by manually entering required values." + "\n" +
                            "2. Generate magic square by automatically." + "\n" +
                            "3. Exit"
            );
            option = Integer.parseInt(s);

            switch (option) {
                case 1:
                    option1();
                    break;

                case 2:
                    option2();
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null,
                            "Thank you for your time. Have a nice day.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Please Enter 1, 2, or 3 to continue.", "Error",
                            JOptionPane.WARNING_MESSAGE);
            }
        } while (option < 1 || option > 3);
    }

    // ----- OPTIONS -----
    public static void option1() {
        String s;           // String to take user input
        int[][] square;     // Square Array
        int n, first, step;


        // Prompting the user to enter the number of values of the perfect square
        do {
            s = JOptionPane.showInputDialog("Enter the number of values in the square. " +
                    "\n(The number of values should be divisible by four, and positive)");
            n = Integer.parseInt(s);

            if (!isPerfectSquare(n) || n < 0)
                JOptionPane.showMessageDialog(null, "Invalid Input." +
                        "\nPlease Try Again.");
        } while (!isPerfectSquare(n) || n < 0);

        // Prompting the user to enter the first value in the square
        do {
            s = JOptionPane.showInputDialog("Enter the first value of the square. " +
                    "\n(Should be positive)");
            first = Integer.parseInt(s);

            if (first < 0)
                JOptionPane.showMessageDialog(null, "Invalid Input." +
                        "\nPlease Try Again.");
        } while (first < 0);

        // Prompting the user to enter the step between every two values
        do {
            s = JOptionPane.showInputDialog("Enter the step between every two values. " +
                    "\n(Should be positive)");
            step = Integer.parseInt(s);

            if (step < 0)
                JOptionPane.showMessageDialog(null, "Invalid Input." +
                        "\nPlease Try Again.");
        } while (step < 0);


        // Building the Square
        System.out.println("Square :");
        buildMagicSquare1(n, first, step);


        System.out.println();
    }

    public static void option2() {
        String s;       // String to take user input
        int[][] square; // Square Array
        int side;       // Side of the square


        do {
            // Prompting the user to enter the number of rows or columns of the square
            s = JOptionPane.showInputDialog("Enter the number of rows or columns in the array." +
                    "\n(Should be odd and positive)");
            side = Integer.parseInt(s);

            if (side % 2 == 0 || side < 0)
                JOptionPane.showMessageDialog(null, "Invalid Input." +
                        "\nPlease Try Again.");
        } while (side % 2 == 0 || side < 0);


        // Building the square
        System.out.println("Square 2:");
        square = buildMagicSquare2(side);
        printMatrix(square);
        if (isMagicSquare(square))
            System.out.println("Magic Square!");
        else
            System.out.println("Not a Magic Square :/");


        System.out.println();
    }


    // ----- METHODS -----
    // Method that constructs a magic square
    public static void buildMagicSquare1(int n, int first, int step) {
        int[]   array1;
        int[][] array2;


        // Calls the first method, and assigns the created array to array1
        array1 = createArithmeticSeq(n, first, step);

       // Calls the third method, and assigns the 2D-array to array2
        array2 = matricize(array1);

        // Calls the fourth method
        reverseDiagonals(array2);

        // Displaying the square
        printMatrix(array2);
        if (isMagicSquare(array2))
            System.out.println("Magic Square!");
        else
            System.out.println("Not a Magic Square :/");
    }


    // Method that tests if its integer parameter represents a square
    public static boolean isPerfectSquare(int n) {

        return (n % 4 == 0);
    }

    // Method used to create an arithmetic sequence of the magic square
    public static int[] createArithmeticSeq(int n, int first, int step) {
        int[] seq;

        seq = new int[n];
        for (int i = 0; i < n; i++) {

            seq[i] = first;
            first += step;
        }

        return seq;
    }


    // Method that displays the integer values of a single-dimension array in
    // a tabular format of a 2D shape
    public static void display1DArray(int valuesPerLine, int[] array) {
        int count;

        count = 0;
        for (int i = 0; i < array.length; i++) {
            count++;

            System.out.print(array[i] + "\t");
            if (count % valuesPerLine == 0)
                System.out.println();
        }
    }


    // Method that takes a given 1D array of integers, and returns the exact same value but in
    // a 2D array before returning.
    public static int[][] matricize(int[] array) {
        int [][] newArray;
        int count;
        int side;


        count    = 0;
        side     = array.length / 4;
        newArray = new int[side][side];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                newArray[i][j] = array[count];

                count++;
            }
        }

        return newArray;
    }
    
    
    // Method that takes a 2D array, and reverses its diagonal values before returning it
    public static void reverseDiagonals(int[][] square) {

        reverseLRDiagonal(square);
        reverseRLDiagonal(square);
    }

    // Method that reverses the diagonals from left to right
    public static void reverseLRDiagonal(int[][] square) {
        int temp;

        for (int i = 0, j = square.length-1; i < square.length/2; i++, j--) {

            temp        = square[i][i];
            square[i][i]= square[j][j];
            square[j][j]= temp;
        }
    }

    // Method that reverses the diagonals from right to left
    public static void reverseRLDiagonal(int[][] square) {
        int temp;

        for (int i = 0, j = square.length-1; i < square.length/2; i++, j--) {

            temp        = square[i][j];
            square[i][j]= square[j][i];
            square[j][i]= temp;
        }
    }



    // Method that builds the second magic square
    public static int[][] buildMagicSquare2(int n) {
        int[][] square = new int[n][n];
        int count;  // Number of values placed in the array
        int x;      // Abscissa
        int y;      // Ordinate

        x = n / 2;
        y = n - 1;
        count = 0;


        for (int i = 1; count < n*n;) {

            if ((x == -1) && (y == n)) {
                x = 0;
                y = n - 2;
            }
            else {
                if (x == -1)
                    x = n - 1;

                if (y == n)
                    y = 0;
            }


            if (square[x][y] != 0){
                x += 1;
                y -= 2;
            }
            else {
                square[x][y] = i;
                i++;

                x--;
                y++;
                count++;
            }
        }


        return square;
    }

    // Method that verifies if the two-dimensional integer array represents a perfect square
    public static boolean isMagicSquare(int[][] arr) {
        int sum;


        sum = sum1DArray(arr[0]);
        for (int[] r : arr)
            if (sum != sum1DArray(r)) {
                return false;
            }

        for (int c = 0; c < arr.length; c++)
            if (sum != sumCol(arr, c)) {
                return false;
            }


        if (sum != sum1stDiagonal(arr)) {
            return false;
        }

        if (sum != sum2ndDiagonal(arr)) {
            return false;
        }


        return true;
    }

    // Method that returns the sum of the values of a single-dimensional array
    public static int sum1DArray(int[] array) {
        int sum;

        sum = 0;
        for (int i : array)
            sum += i;

        return sum;
    }

    // Method that returns the sum of the values in a column of a two-dimensional array
    public static int sumCol(int[][] array, int col) {
        int sum;

        sum = 0;
        for (int i = 0; i < array.length; i++)
            sum += array[i][col];

        return sum;
    }
    
    // Method that returns the sum of the first diagonal of a 2D-array
    public static int sum1stDiagonal(int[][] square) {
        int sum;

        sum = 0;
        for (int i = 0; i < square.length; i++)
            sum += square[i][i];

        return sum;
    }

    // Method that returns the sum of the second diagonal of a 2D-array
    public static int sum2ndDiagonal(int[][] square) {
        int sum;

        sum = 0;
        for (int i = square.length-1, j = 0; i >= 0; i--, j++)
            sum += square[i][j];

        return sum;
    }


    // Method that outputs the elements of a two-dimensional array
    public static void printMatrix(int[][] array) {

        for (int[] i : array)
            display1DArray(i.length, i);
    }
}
