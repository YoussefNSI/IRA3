from time import time as t

listi = [i for i in range(1000000)]
list2 = [1,2,3]
t1 = t()
print(999999 in listi)
t2 = t()
print(3 in list2)

tuple1 = tuple(i for i in range(1000000))
tuple2 = (1,2,3)
t3 = t()
print(999999 in tuple1)
t4 = t()
print(3 in tuple2)


print("resumé des temps d'execution:")
print(f"Liste: {t2 - t1}")
print(f"Tuple: {t4 - t3}")
