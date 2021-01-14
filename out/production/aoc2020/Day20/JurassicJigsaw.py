def rotgrid(g):
    ng = []
    n = len(g)
    for i in range(n):
        ng.append(['#'] * n)
    for i in range(n):
        for j in range(n):
            ng[j][n-1-i] = g[i][j]
    return ng

def flipgrid(g):
    ng = []
    n = len(g)
    for i in range(n):
        ng.append(g[i][::-1])
    return ng

def getforcedrotations(g):
    ret = []
    ret.append(g)
    ret.append(rotgrid(ret[-1]))
    ret.append(rotgrid(ret[-1]))
    ret.append(rotgrid(ret[-1]))
    ret.append(flipgrid(g))
    ret.append(rotgrid(ret[-1]))
    ret.append(rotgrid(ret[-1]))
    ret.append(rotgrid(ret[-1]))
    return ret

"""
NOT 2509
"""

def force(g):
    pattern = """
                  # 
#    ##    ##    ###
 #  #  #  #  #  #   
""".split("\n")
    pattern = [x for x in pattern if x]
    for realgrid in getforcedrotations(g):
        used = []
        for row in realgrid:
            used.append([False] * len(row))
        i = 0
        while i + len(pattern) <= len(realgrid):
            j = 0
            while j + len(pattern[0]) <= len(realgrid[i]):
                good = True
                for a in range(len(pattern)):
                    for b in range(len(pattern[a])):
                        if pattern[a][b] == '#' and realgrid[i+a][j+b] != '#':
                            good = False
                if good:
                    for a in range(len(pattern)):
                        for b in range(len(pattern[a])):
                            if pattern[a][b] == '#':
                                used[i+a][j+b] = True
                j += 1
            i += 1
        ret = 0
        for i in range(len(realgrid)):
            for j in range(len(realgrid[i])):
                if realgrid[i][j] == '#' and not used[i][j]:
                    ret += 1
        print(ret)

def part2(g):
    n = len(g)
    realgrid = []
    for i in range(n):
        for j in range(n):
            smallgrid = []
            for k in range(10):
                if k in [0, 9]:
                    continue
                cand = []
                for a in range(10):
                    if k in [0, 9] or a in [0, 9]:
                        continue
                    if g[i][j][1] & (2 ** (99 - (10 * k + a))):
                        cand.append('#')
                    else:
                        cand.append('.')
                smallgrid.append(cand)
            srow = len(smallgrid) * i
            for rowi, row in enumerate(smallgrid):
                while srow + rowi >= len(realgrid):
                    realgrid.append([])
                realgrid[srow+rowi].extend(row)
    force(realgrid)

def rot(mask):
    val = 0
    for i in range(10):
        for j in range(10):
            idx = 10 * j + 9 - i
            if mask & (2**(10*i+j)):
                val |= 2 ** idx
    return val
def flip(mask):
    val = 0
    for i in range(10):
        for j in range(10):
            idx = 10 * i + 9 - j
            if mask & (2**(10*i+j)):
                val |= 2 ** idx
    return val

def getallrotations(mask):
    ret = []
    ret.append(mask)
    ret.append(rot(ret[-1]))
    ret.append(rot(ret[-1]))
    ret.append(rot(ret[-1]))
    ret.append(flip(mask))
    ret.append(rot(ret[-1]))
    ret.append(rot(ret[-1]))
    ret.append(rot(ret[-1]))
    return ret

def gettop(mask):
    idx = 99
    val = 0
    for i in range(10):
        val *= 2
        if mask & (2**idx):
            val += 1
        idx -= 1
    return val
def getbottom(mask):
    return mask & 1023
def getleft(mask):
    val = 0
    idx = 99
    for i in range(10):
        val *= 2
        if mask & (2 ** idx):
            val += 1
        idx -= 10
    return val
def getright(mask):
    val = 0
    idx = 90
    for i in range(10):
        val *= 2
        if mask & (2 ** idx):
            val += 1
        idx -= 10
    return val

def dfs(g, r, c, l, used):
    if r == len(g):
        part2(g)
        print(g[0][0][0] * g[0][-1][0] * g[-1][0][0] * g[-1][-1][0])
        exit(0)
        return
    if c == len(g[r]):
        dfs(g, r+1, 0, l, used)
        return
    for x, val in l:
        if x in used:
            continue
        for cand in getallrotations(val):
            good = True
            if r > 0:
                good &= gettop(cand) == getbottom(g[r-1][c][1])
            if c > 0:
                good &= getleft(cand) == getright(g[r][c-1][1])
            if good:
                g[r][c] = (x, cand)
                used.add(x)
                dfs(g, r, c+1, l, used)
                used.discard(x)
                assert x not in used
                g[r][c] = -1

l = []
while True:
    try:
        s = input()
        assert s.startswith("Tile")
        s = s[5:-1]
        x = int(s)
        val = 0
        for i in range(10):
            s = input()
            for j in range(10):
                val *= 2
                if s[j] == '#':
                    val += 1
        l.append((x, val))
        input()
    except EOFError:
        break

s = 1
while s * s < len(l):
    s += 1
assert s*s == len(l), "{} {}".format(s, len(l))
g = []
for i in range(s):
    g.append([-1] * s)
used = set()
dfs(g, 0, 0, l, used)