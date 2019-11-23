class Database
	def initialize(ui)
		@@data = []
		ui.show( "Loading database", "info")
		text = File.open("names.txt").read
		text.gsub!(/\r\n?/, "\n")

		text.each_line do |line|
			i = line.strip.split(",")
			@@data.push( { i[0] => i[1] } )
		end
		ui.show( "Database loaded", "info")
	end
	
	def Search(name)
		for p in @@data
			if p.values[0] == name
				return p.keys[0]
			end
		end
		return false
	end
end