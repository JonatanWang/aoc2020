rules = {}
f = map(lambda s: s.strip(), open('input.txt', 'r'))
for line in f:
    if not line:
        break
    r = line.split()
    rules[r[0][:-1]] = ' '.join(r[1:])
messages = list(f)

part_2 = True

if part_2:
    rules['8'] = '42 | 42 8'
    rules['11'] = '42 31 | 42 11 31'

def parse(rule_name, message):
    rule = rules[rule_name]
    if len(rule) == 3:
        if not message:
            return []
        if message[0] == rule[1]:
            return [message[1:]]
        return []
    suffixes = []
    for alt in rule.split('|'):
        alt_suffixes = [message]
        for part in alt.split():
            alt_suffixes = [new_suffix for suffix in alt_suffixes for new_suffix in parse(part, suffix)]
            if not alt_suffixes:
                break
        suffixes += alt_suffixes
    return suffixes

print(sum(1 for message in messages if '' in parse('0', message)))