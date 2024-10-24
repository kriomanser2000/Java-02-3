import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Array<T extends Number & Comparable<T>>
{
    private T[] array;
    private int size;
    public Array(int size)
    {
        this.size = size;
        this.array = (T[]) new Number[size];
    }
    public void fillFromKeyboard(Scanner scanner)
    {
        for (int i = 0; i < size; i++)
        {
            System.out.print("Enter element #" + (i + 1) + ": ");
            array[i] = (T) (Number) scanner.nextDouble();
        }
    }
    public void fillWithRandom(Random random, double minValue, double maxValue)
    {
        for (int i = 0; i < size; i++)
        {
            double randomValue = minValue + (maxValue - minValue) * random.nextDouble();
            array[i] = (T) (Number) randomValue;
        }
    }
    public void printArray()
    {
        System.out.println(Arrays.toString(array));
    }
    public T findMax()
    {
        T max = array[0];
        for (int i = 1; i < size; i++)
        {
            if (array[i].compareTo(max) > 0)
            {
                max = array[i];
            }
        }
        return max;
    }
    public T findMin()
    {
        T min = array[0];
        for (int i = 1; i < size; i++)
        {
            if (array[i].compareTo(min) < 0)
            {
                min = array[i];
            }
        }
        return min;
    }
    public double findAverage()
    {
        double sum = 0;
        for (T num : array)
        {
            sum += num.doubleValue();
        }
        return sum / size;
    }
    public void sortAscending()
    {
        Arrays.sort(array);
    }
    public void sortDescending()
    {
        Arrays.sort(array, (a, b) -> b.compareTo(a));
    }
    public int binarySearch(T key)
    {
        return Arrays.binarySearch(array, key);
    }
    public void replaceValue(int index, T newValue)
    {
        if (index >= 0 && index < size)
        {
            array[index] = newValue;
        }
        else
        {
            System.out.println("Index out of bounds.");
        }
    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        Array<Double> myArray = new Array<>(size);
        myArray.fillWithRandom(random, 0, 100);
        System.out.println("Random array:");
        myArray.printArray();
        System.out.println("\nFill array from keyboard:");
        myArray.fillFromKeyboard(scanner);
        System.out.println("Array:");
        myArray.printArray();
        System.out.println("Max value: " + myArray.findMax());
        System.out.println("Min value: " + myArray.findMin());
        System.out.println("Average value: " + myArray.findAverage());
        myArray.sortAscending();
        System.out.println("Sorted array (ascending):");
        myArray.printArray();
        myArray.sortDescending();
        System.out.println("Sorted array (descending):");
        myArray.printArray();
        System.out.print("Enter a value to search: ");
        double searchValue = scanner.nextDouble();
        int index = myArray.binarySearch(searchValue);
        if (index >= 0)
        {
            System.out.println("Value found at index: " + index);
        }
        else
        {
            System.out.println("Value not found.");
        }
        System.out.print("Enter index to replace: ");
        int replaceIndex = scanner.nextInt();
        System.out.print("Enter new value: ");
        double newValue = scanner.nextDouble();
        myArray.replaceValue(replaceIndex, newValue);
        System.out.println("Updated array:");
        myArray.printArray();
        scanner.close();
    }
}
