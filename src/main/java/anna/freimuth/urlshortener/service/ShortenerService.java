package anna.freimuth.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

    private static final String base ="wmdganyvci5hk12p7ufr90x3qjts4lzo8b6e";  // 36 characters base
    private static final int baseLength = base.length();


    public String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ) {
            sb.append( base.charAt( (int) (num % baseLength) ) );
            num /= baseLength;
        }

        if (sb.length() == 0)
            return "w";
        return sb.reverse().toString();
    }

    public long decode(String str) {
        long num = 0;
        for ( int i = 0; i < str.length(); i++ )
            num = num * baseLength + base.indexOf(str.charAt(i));
        return num;
    }

}
