public class LabN3 {
    int count = 0;
    int[] numbers;

    public LabN3(int n){
        this.numbers = new int[n];
    }

    public void addNum(int num){
        this.numbers[count] = num;
        count++;
        showSortedUp();
    }

    public void showSortedUp(){
        int n = this.numbers.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if(this.numbers[j] > this.numbers[j+1]){
                    int temp = this.numbers[j];
                    this.numbers[j] = this.numbers[j+1];
                    this.numbers[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(this.numbers));
    }

    //через for
    public void showSortedDown(){
        int n = this.numbers.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if(this.numbers[j] < this.numbers[j+1]){
                    int temp = this.numbers[j];
                    this.numbers[j] = this.numbers[j+1];
                    this.numbers[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(this.numbers));
    }

    //сколько каждые
    public String Chastotnost(int number){
        int count = 0;
        int n = this.numbers.length;
        for (int i = 0; i < n; i++) {
            if(this.numbers[i] == number) count++;
        }
        return (number + " - " + count);
    }

    //запоминать вызывал или нет
    public String Chastotnost1(){
        for (int i = 0; i < count; i++) {

        }
        return "";
    }

    public int Razmax(){
        int a = this.numbers[0];
        int b = this.numbers[0];
        for (int i = 1; i < this.numbers.length; i++) {
            if(numbers[i] > a) a = numbers[i];
        }
        for (int i = 1; i < this.numbers.length; i++) {
            if (numbers[i] < b) b = numbers[i];
        }
        return (a - b);
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
        int s = 1;
        for (int i = 0; i < count; i++) {
            s*=numbers[i];
        }
        return Math.pow(s, 1.0 /numbers.length);
    }

    public double sredneeGarmonicheskoye(){
        if(count == 0) return 0;
        double zn = 0;
        for (int i = 0; i < count; i++) {
            zn += 1/numbers[i];
        }
        return count / zn;
    }

    public int findOneMode() {
        if (count == 0) return 0;

        int mode = numbers[0];
        int maxCount = 1;

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

        return mode;
    }

    public void printModeInfo() {
        int mode = findOneMode();
        if (mode == 0 && count == 0) {
            System.out.println("Массив пуст!");
        } else {
            // Считаем частоту моды
            int freq = 0;
            for (int i = 0; i < count; i++) {
                if (numbers[i] == mode) {
                    freq++;
                }
            }
            System.out.println("Мода: " + mode + " (встречается " + freq + " раз)");
        }
    }

    //привести к double
    public double findMediana(){
        if((this.numbers.length & 1) == 0) return this.numbers[numbers.length/2 - 1];
        return this.numbers[((numbers.length-1)/2 + (numbers.length+1)/2)/2];
    }

    public double variance() {
        if(count == 0) return 0;

        double mean = sredneeArifm();
        double sumSquaredDifferences = 0;

        for(int i = 0; i < count; i++) {
            double difference = numbers[i] - mean;
            sumSquaredDifferences += difference * difference;
        }

        return sumSquaredDifferences / count;
    }

    public double standardDeviation() {
        if(count == 0) return 0;

        return Math.sqrt(variance());
    }

    // Дисперсия альтернативного признака (четность чисел)
    public double alternativeVariance() {
        if(count == 0) return 0;

        // Считаем долю четных чисел
        int evenCount = 0;
        for(int i = 0; i < count; i++) {
            if(numbers[i] % 2 == 0) {
                evenCount++;
            }
        }

        double p = (double) evenCount / count; // доля четных чисел
        double q = 1 - p; // доля нечетных чисел

        // Дисперсия альтернативного признака: p * q
        return p * q;
    }
}



void main() {
    LabN3 lab = new LabN3(5);

}
