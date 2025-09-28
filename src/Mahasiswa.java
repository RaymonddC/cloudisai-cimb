/**
 * Student class with name, student ID, and grade attributes
 */
public class Mahasiswa {
    public String nama;
    public String nim;
    public double nilai;

    /**
     * Sets student data
     */
    public void setData(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }

    /**
     * Check if student passes (grade > 70)
     */
    public boolean isLulus() {
        return nilai > 70;
    }
}
