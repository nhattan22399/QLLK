package MenuItem;

public class Nhansu {
    public String ten;
    public int mans;
    public String chucvu;
    public byte[] anh;

    public Nhansu(int mans,String ten, String chucvu, byte[] anh) {
        this.ten = ten;
        this.mans = mans;
        this.chucvu = chucvu;
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMans() {
        return mans;
    }

    public void setMans(int mans) {
        this.mans = mans;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
