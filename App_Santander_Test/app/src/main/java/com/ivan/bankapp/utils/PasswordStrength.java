package com.ivan.bankapp.utils;

import android.graphics.Color;

import com.ivan.bankapp.R;

public enum PasswordStrength {


        WEAK(R.string.fraca, Color.parseColor("#61ad85")),
        MEDIUM(R.string.media, Color.parseColor("#4d8a6a")),
        STRONG(R.string.forte, Color.parseColor("#3a674f")),
        VERY_STRONG(R.string.muitoForte, Color.parseColor("#264535"));

        public int msg;
        public int color;
        public int passwordScore;
        private static int MIN_LENGTH = 8;
        private static int MAX_LENGTH = 15;

        PasswordStrength(int msg, int color) {
            this.msg = msg;
            this.color = color;
        }

        public static PasswordStrength calculate(String password) {
            int score = 0;
            // boolean indicating if password has an upper case
            boolean upper = false;
            // boolean indicating if password has a lower case
            boolean lower = false;
            // boolean indicating if password has at least one digit
            boolean digit = false;
            // boolean indicating if password has a leat one special char
            boolean specialChar = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (!specialChar  &&  !Character.isLetterOrDigit(c)) {
                    score++;
                    specialChar = true;
                } else {
                    if (!digit  &&  Character.isDigit(c)) {
                        score++;
                        digit = true;
                    } else {
                        if (!upper || !lower) {
                            if (Character.isUpperCase(c)) {
                                upper = true;
                            } else {
                                lower = true;
                            }

                            if (upper && lower) {
                                score++;
                            }
                        }
                    }
                }
            }

            int length = password.length();

            if (length > MAX_LENGTH) {
                score++;
            } else if (length < MIN_LENGTH) {
                score = 0;
            }

            // return enum following the score
            switch(score) {
                case 0 : return WEAK;
                case 1 : return MEDIUM;
                case 2 : return STRONG;
                case 3 : return VERY_STRONG;
                default:
            }

            return VERY_STRONG;
        }
}
