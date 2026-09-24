package leetcode.string;

public class Jz58 {

    public static void main(String[] args) {
        System.out.println(new Jz58().reverse("abcdefg", 2));
    }

    public String reverse(String s, int cnt) {
        String leftS = s.substring(0, cnt);
        String rightS = s.substring(cnt);
        return rightS + leftS;
    }

    public String revers(String s, int cnt) {
        int j = s.length() - cnt;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < cnt; i++) {
            char tmp = s.charAt(j);
            sb.setCharAt(j, sb.charAt(i));
            sb.setCharAt(i, tmp);
        }
        return sb.toString();
    }
}
