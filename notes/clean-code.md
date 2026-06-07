
# Clean Code Tip

Useful for reference: For method names of recursion functions, you can write it like computerXYZ, buildXYZ, findLongestSubstring, helper, etc.
Or other suggestions can be solveLcs, etc.


Tips for simplifying code like this in interviews
- Push boundary checks into a helper. Most "special case" branches (if (r==0)...) are really just out-of-bounds guards. A tiny accessor like lcs(dp, r, c) -> (r<0||c<0)?0:dp[r][c] collapses many branches into one. This is the single biggest simplifier for grid/DP backtracking.
- Keep one consistent direction. Mixing "append forward here, append reversed there" is where bugs hide. Pick one order (here: always backward, reverse once at the end) and make every code path obey it.
- Prefer while (i >= 0) append(s.charAt(i--)) over substring + reverse. Char-by-char draining is harder to get wrong and matches the loop's order naturally.


