import sys
import argparse


def count_bytes(file_content):
	byte_count = len(file_content)
	return byte_count


def count_lines(file_content):
	line_count = file_content.count(b'\n')
	return line_count


def count_words(file_content):
	words = file_content.decode('utf-8').split()
	words_count = len(words)
	return words_count


def count_characters(file_content):
	char_count = len(file_content.decode('utf-8'))
	return char_count


def main():
	parser = argparse.ArgumentParser(
		prog='Custom Word Count Tool by @abidmuin',
		description='Print bytes, lines, words and characters counts for each FILE',
		epilog='Author: https://github.com/abidmuin',
		formatter_class=argparse.RawDescriptionHelpFormatter,
	)
	parser.add_argument('-c', '--bytes', action='store_true', help='print the byte counts')
	parser.add_argument('-l', '--lines', action='store_true', help='print the newline counts')
	parser.add_argument('-w', '--words', action='store_true', help='print the word counts')
	parser.add_argument('-m', '--chars', action='store_true', help='print the character counts')
	parser.add_argument('file', metavar='file', type=str, nargs='?', help='File path')
	args = parser.parse_args()

	if args.file:
		with open(args.file, 'rb') as file:
			file_content = file.read()
	elif not sys.stdin.isatty():
		file_content = sys.stdin.buffer.read()
	else:
		print("No standard input found.")
		sys.exit(1)

	if args.bytes:
		result = count_bytes(file_content)
		if args.file:
			print(f'{result} {args.file}')
		else:
			print(result)
	elif args.lines:
		result = count_lines(file_content)
		if args.file:
			print(f'{result} {args.file}')
		else:
			print(result)
	elif args.words:
		result = count_words(file_content)
		if args.file:
			print(f'{result} {args.file}')
		else:
			print(result)
	elif args.chars:
		result = count_characters(file_content)
		if args.file:
			print(f'{result} {args.file}')
		else:
			print(result)
	else:
		bytes_count = count_bytes(file_content)
		lines_count = count_lines(file_content)
		words_count = count_words(file_content)
		if args.file:
			print(f'{bytes_count} {lines_count} {words_count} {args.file}')
		else:
			print(f'{bytes_count} {lines_count} {words_count}')


if __name__ == '__main__':
	main()
