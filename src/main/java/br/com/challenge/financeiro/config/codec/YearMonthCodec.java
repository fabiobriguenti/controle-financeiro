package br.com.challenge.financeiro.config.codec;

import java.time.YearMonth;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class YearMonthCodec implements Codec<YearMonth> {
	
	@Override
	public void encode(BsonWriter writer, YearMonth value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        
        writer.writeInt32("year", value.getYear());
        writer.writeInt32("month", value.getMonthValue());
        
        writer.writeEndDocument();
    }
	
	@Override
    public Class<YearMonth> getEncoderClass() {
        return YearMonth.class;
    }
	
	@Override
    public YearMonth decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        
        Integer ano = reader.readInt32("year");
        Integer mes = reader.readInt32("month");
        
        reader.readEndDocument();
        
        return YearMonth.of(ano, mes);
    }
}