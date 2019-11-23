class Database:
	data = []
	def __init__(self, ui):
		ui.show( "Loading database", "info")
		with open("names.txt") as f:
			text = f.readlines()
			lines = [line.rstrip('\n') for line in text]
			self.data = [line.split(',') for line in lines]
		ui.show( "Database loaded", "info")
	
	def Search(self, name):
		for p in self.data:
			if p[1] == name:
				return p[0]
		return False