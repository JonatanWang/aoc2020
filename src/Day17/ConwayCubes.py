def play(data, dim, moves=6):
    for x in range(moves + 1): on = set(
        (x, y, *(0,) * (dim - 2)) for x, y in __import__("itertools").product(range(len(data)), range(len(data[0]))) if
        data[y][x] == "#") if x == 0 else {cube_pos for cube_pos in __import__("itertools").product(*[
        range([min(cube[di] for cube in on) for di in range(dim)][di] - 1,
              2 + [max(cube[di] for cube in on) for di in range(dim)][di]) for di in range(dim)]) if len(
        [neighbour_offset for neighbour_offset in
         set(__import__("itertools").product((-1, 0, 1), repeat=dim)) - {(0,) * dim} if
         tuple(map(sum, zip(cube_pos, neighbour_offset))) in on]) == 3 or cube_pos in on and len(
        [neighbour_offset for neighbour_offset in
         set(__import__("itertools").product((-1, 0, 1), repeat=dim)) - {(0,) * dim} if
         tuple(map(sum, zip(cube_pos, neighbour_offset))) in on]) == 2}

    return len(on)


print(
    *[f"Part {part}: {play(open('input.txt').read().splitlines(), dim)}" for part, dim in enumerate((3, 4), 1)],
    sep="\n")
