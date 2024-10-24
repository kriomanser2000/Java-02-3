import java.util.Random;
import java.util.Scanner;

public class Matrix<T extends Number>
{
    private T[][] matrix;
    private int rows;
    private int cols;
    public Matrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.matrix = (T[][]) new Number[rows][cols];
    }
    public void fillMatrixFromInput(Scanner scanner)
    {
        System.out.println("Enter matrix elements: ");
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                System.out.print("Elements [" + i + "][" + j + "]: ");
                matrix[i][j] = (T) Double.valueOf(scanner.nextDouble());
            }
        }
    }
    public void fillMatrixWithRandom()
    {
        Random random = new Random();
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                matrix[i][j] = (T) Double.valueOf(random.nextDouble() * 100);
            }
        }
    }
    public void displayMatrix()
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public Matrix<Double> add(Matrix<T> other)
    {
        return arithmeticOperation(other, '+');
    }
    public Matrix<Double> subtract(Matrix<T> other)
    {
        return arithmeticOperation(other, '-');
    }
    public Matrix<Double> multiply(Matrix<T> other)
    {
        if (this.cols != other.rows)
        {
            throw new IllegalArgumentException("Unable to multiply matrix: sizes do not match.");
        }
        Matrix<Double> result = new Matrix<>(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < other.cols; j++)
            {
                double sum = 0;
                for (int k = 0; k < this.cols; k++)
                {
                    sum += matrix[i][k].doubleValue() * other.matrix[k][j].doubleValue();
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }
    public Matrix<Double> divide(Matrix<T> other)
    {
        return arithmeticOperation(other, '/');
    }
    private Matrix<Double> arithmeticOperation(Matrix<T> other, char operation)
    {
        if (this.rows != other.rows || this.cols != other.cols)
        {
            throw new IllegalArgumentException("The size of matrix must be same.");
        }
        Matrix<Double> result = new Matrix<>(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.cols; j++)
            {
                switch (operation)
                {
                    case '+':
                        result.matrix[i][j] = matrix[i][j].doubleValue() + other.matrix[i][j].doubleValue();
                        break;
                    case '-':
                        result.matrix[i][j] = matrix[i][j].doubleValue() - other.matrix[i][j].doubleValue();
                        break;
                    case '/':
                        result.matrix[i][j] = matrix[i][j].doubleValue() / other.matrix[i][j].doubleValue();
                        break;
                }
            }
        }
        return result;
    }
    public T maxElement()
    {
        T max = matrix[0][0];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (matrix[i][j].doubleValue() > max.doubleValue())
                {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }
    public T minElement()
    {
        T min = matrix[0][0];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (matrix[i][j].doubleValue() < min.doubleValue())
                {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }
    public double averageValue()
    {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                sum += matrix[i][j].doubleValue();
                count++;
            }
        }
        return sum / count;
    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Matrix<Double> matrix1 = new Matrix<>(3, 3);
        matrix1.fillMatrixFromInput(scanner);
        matrix1.displayMatrix();
        Matrix<Double> matrix2 = new Matrix<>(3, 3);
        matrix2.fillMatrixWithRandom();
        System.out.println("Random matr: ");
        matrix2.displayMatrix();
        Matrix<Double> sum = matrix1.add(matrix2);
        System.out.println("Matrix sum: :");
        sum.displayMatrix();
        System.out.println("Max element: " + matrix1.maxElement());
        System.out.println("Min element: " + matrix1.minElement());
        System.out.println("Arithmetic mean: " + matrix1.averageValue());
    }
}
