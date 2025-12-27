import java.util.Arrays;

public class LabN3 {
    int count = 0;
    int[] numbers;

    public LabN3(int n){
        this.numbers = new int[n];
    }

    public void addNum(int num){
        if (count < numbers.length) {
            this.numbers[count] = num;
            count++;
            showSortedUp();
        } else {
            System.out.println("Массив заполнен! Нельзя добавить больше элементов.");
        }
    }

    public void showSortedUp(){
        int n = count;
        int[] tempArray = Arrays.copyOf(numbers, count);

        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if(tempArray[j] > tempArray[j+1]){
                    int temp = tempArray[j];
                    tempArray[j] = tempArray[j+1];
                    tempArray[j+1] = temp;
                }
            }
        }
        System.out.println("Сортировка по возрастанию: " + Arrays.toString(tempArray));
    }

    public void showSortedDown(){
        int n = count;
        int[] tempArray = Arrays.copyOf(numbers, count);

        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if(tempArray[j] < tempArray[j+1]){
                    int temp = tempArray[j];
                    tempArray[j] = tempArray[j+1];
                    tempArray[j+1] = temp;
                }
            }
        }
        System.out.println("Сортировка по убыванию: " + Arrays.toString(tempArray));
    }


    public String Chastotnost(int number){
        int freq = 0;
        for (int i = 0; i < count; i++) {
            if(this.numbers[i] == number) freq++;
        }
        return (number + " - " + freq);
    }

    public String Chastotnost1(){
        if (count == 0) return "Массив пуст";

        StringBuilder result = new StringBuilder();
        boolean[] processed = new boolean[count];

        for (int i = 0; i < count; i++) {
            if (!processed[i]) {
                int currentNum = numbers[i];
                int freq = 0;

                for (int j = 0; j < count; j++) {
                    if (numbers[j] == currentNum) {
                        freq++;
                        processed[j] = true;
                    }
                }
                result.append(currentNum).append(" - ").append(freq).append("\n");
            }
        }
        return result.toString();
    }

    public int Razmax(){
        if (count == 0) return 0;

        int max = numbers[0];
        int min = numbers[0];

        for (int i = 1; i < count; i++) {
            if(numbers[i] > max) max = numbers[i];
            if(numbers[i] < min) min = numbers[i];
        }
        return (max - min);
    }

    //нули до count
    public double sredneeArifm(){
        if(count == 0) return 0;
        double s = 0;
        for (int i = 0; i < count; i++) {
            s += numbers[i];
        }
        return s / count;
    }

    public double sredneeGeom(){
        if(count == 0) return 0;
        double s = 1.0;
        for (int i = 0; i < count; i++) {
            s *= numbers[i];
        }
        if(count % 2 == 0 && s < 0){
            System.out.println("Нельзя взять корень из отрицательного числа");
            return -1;
        }
        return Math.pow(s, 1.0 / count);
    }

    public double sredneeGarmonicheskoye(){
        if(count == 0) return 0;
        double zn = 0;
        boolean hasZero = false;

        for (int i = 0; i < count; i++) {
            if (numbers[i] == 0) {
                hasZero = true;
                break;
            }
            zn += 1.0 / numbers[i];
        }

        if (hasZero) {
            System.out.println("Внимание: гармоническое среднее не определено при наличии нулей!");
            return 0;
        }
        return count / zn;
    }

    public int findOneMode() {
        if (count == 0) return 0;

        int mode = numbers[0];
        int maxCount = 0;

        for (int i = 0; i < count; i++) {
            int currentCount = 0;
            for (int j = 0; j < count; j++) {
                if (numbers[j] == numbers[i]) {
                    currentCount++;
                }
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                mode = numbers[i];
            }
        }

        //если все числа встречаются по 1 разу
        if (maxCount == 1 && count > 1) {
            System.out.print("Все числа уникальны. ");
        }

        return mode;
    }

    public void printModeInfo() {
        if (count == 0) {
            System.out.println("Массив пуст!");
            return;
        }

        // 1. Сортируем массив для подсчета
        int[] sorted = this.numbers.clone();
        Arrays.sort(sorted);

        // 2. Считаем максимальную частоту и количество мод
        int maxCount = 0;
        int modesCount = 0;
        int currentCount = 1;

        // Первый проход: находим максимальную частоту и количество мод
        for (int i = 1; i <= sorted.length; i++) {
            if (i < sorted.length && sorted[i] == sorted[i - 1]) {
                currentCount++;
            } else {
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    modesCount = 1;
                } else if (currentCount == maxCount) {
                    modesCount++;
                }
                currentCount = 1;
            }
        }

        // 3. Создаем массив для мод
        int[] modes = new int[modesCount];
        int modeIndex = 0;
        currentCount = 1;

        // Второй проход: заполняем массив мод
        for (int i = 1; i <= sorted.length; i++) {
            if (i < sorted.length && sorted[i] == sorted[i - 1]) {
                currentCount++;
            } else {
                if (currentCount == maxCount) {
                    modes[modeIndex++] = sorted[i - 1];
                }
                currentCount = 1;
            }
        }

        // 4. Выводим результат
        System.out.print("Моды: ");
        for (int mode : modes) {
            System.out.print(mode + " ");
        }
    }

    public double findMediana(){
        if (count == 0) return 0;

        //создаем отсортированную копию
        int[] sorted = Arrays.copyOf(numbers, count);
        Arrays.sort(sorted);

        if (count % 2 == 1) {
            //нечетное количество
            return sorted[count / 2];
        } else {
            //чётное количество
            int mid1 = sorted[count / 2 - 1];
            int mid2 = sorted[count / 2];
            return (mid1 + mid2) / 2.0;
        }
    }

    public double variance() {
        if (count == 0) return 0;

        double mean = sredneeArifm();
        double sumSquaredDifferences = 0;

        for(int i = 0; i < count; i++) {
            double difference = numbers[i] - mean;
            sumSquaredDifferences += difference * difference;
        }

        return sumSquaredDifferences / count;
    }

    public double standardDeviation() {
        if (count == 0) return 0;
        return Math.sqrt(variance());
    }

    //дисперсия альтернативного признака (четность чисел)
    public double alternativeVariance() {
        if (count == 0) return 0;

        int evenCount = 0;
        for(int i = 0; i < count; i++) {
            if(numbers[i] % 2 == 0) {
                evenCount++;
            }
        }

        double p = (double) evenCount / count;
        double q = 1 - p;

        return p * q;
    }

    public int getEvenCount() {
        int even = 0;
        for (int i = 0; i < count; i++) {
            if (numbers[i] % 2 == 0) even++;
        }
        return even;
    }

    public int getOddCount() {
        return count - getEvenCount();
    }

    public void displayAllCharacteristics() {
        System.out.println("Исходные данные: " + Arrays.toString(Arrays.copyOf(numbers, count)));
        System.out.println("Количество элементов: " + count);

        if (count == 0) {
            System.out.println("Массив пуст. Добавьте элементы для анализа.");
            return;
        }

        System.out.println("Размах ряда: " + Razmax());

        System.out.printf("Среднее арифметическое: %.4f\n", sredneeArifm());
        System.out.printf("Среднее геометрическое: %.4f\n", sredneeGeom());
        if (sredneeGarmonicheskoye() != 0) {
            System.out.printf("Среднее гармоническое: %.4f\n", sredneeGarmonicheskoye());
        }

        printModeInfo();
        System.out.printf("Медиана: %.4f\n", findMediana());

        System.out.printf("Дисперсия: %.4f\n", variance());
        System.out.printf("Среднеквадратичное отклонение: %.4f\n", standardDeviation());

        System.out.printf("Дисперсия альтернативного признака: %.4f\n", alternativeVariance());
        System.out.printf("Четных чисел: %d \n", getEvenCount());
        System.out.printf("Нечетных чисел: %d \n", getOddCount());

        if (count <= 15) {
            System.out.print(Chastotnost1());
        }

    }
}

class Main {
    public static void main(String[] args) {
        LabN3 lab = new LabN3(5);
        lab.addNum(1);
        lab.addNum(2);
        lab.addNum(0);
        lab.addNum(2);
        lab.addNum(1);
        lab.displayAllCharacteristics();


    }
}